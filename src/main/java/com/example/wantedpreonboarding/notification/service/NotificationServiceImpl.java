package com.example.wantedpreonboarding.notification.service;

import com.example.wantedpreonboarding.notification.dto.NotificationDto;
import com.example.wantedpreonboarding.notification.entity.NotificationEntity;
import com.example.wantedpreonboarding.notification.repository.EmitterRepository;
import com.example.wantedpreonboarding.notification.repository.NotificationRepository;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private static final Long TIME_OUT = 60L * 60 * 24 * 7; // 7일
    private final NotificationRepository notificationRepository;
    private final EmitterRepository emitterRepository;

    @Override
    public SseEmitter subscribe(String username, String lastEventId) {
        String emitterId = username + "_" + System.currentTimeMillis();
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(TIME_OUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));
        return null;
    }

    @Override
    public void send(UserEntity user, NotificationEntity.NotificationType notificationType, String content) {
        NotificationEntity notify = notificationRepository.save(
                NotificationEntity.builder()
                        .notificationType(notificationType)
                        .notifyContent(content)
                        .userEntity(user)
                    .build()
        );
        String userEmail = user.getUserEmail();
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(userEmail);
        String eventId = userEmail + "_" + System.currentTimeMillis();
        emitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notify);
                    sendNotification(emitter, eventId, key, NotificationDto.builder()
                            .id(notify.getNotifyId())
                            .type(notify.getNotificationType())
                            .content(notify.getNotifyContent())
                            .build());
                }
        );
    }

    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data){
        try{
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("sse")
                    .data(data)
            );
        } catch (IOException e) {
            emitterRepository.deleteById(emitterId);
        }
    }

    private boolean hasLostData(String lastEventId){
        return !lastEventId.isEmpty();
    }

    private void sendLostData(String lastEventId, String userEmail, String emitterId, SseEmitter emitter) {
        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByMemberId(userEmail);
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
    }
}
