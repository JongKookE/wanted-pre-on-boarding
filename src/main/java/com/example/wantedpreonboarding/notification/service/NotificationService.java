package com.example.wantedpreonboarding.notification.service;

import com.example.wantedpreonboarding.notification.entity.NotificationEntity.NotificationType;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
    SseEmitter subscribe(String username, String lastEventId);
    void send(UserEntity user, NotificationType notificationType, String content);
}
