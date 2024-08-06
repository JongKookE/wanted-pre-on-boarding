package com.example.wantedpreonboarding.notification.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository{
    // 동시성 문제에서 안전한 ConcurrentHashMap을 사용함
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    // 알람을 받을 사용자를 식별자 키로 저장하며, 해당 사용자에게 전송되지 못한 이벤트를 캐시로 저장
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void saveEventCache(String emitterId, Object event) {
        eventCache.put(emitterId, event);
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String memberId) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(memberId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(memberId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(String id) {
        emitters.remove(id);
    }

    @Override
    public void deleteAllEmitterStartWithId(String memberId) {
        emitters.forEach((key, emitter) -> {
            if(key.startsWith(memberId)) emitters.remove(key);
        });

    }

    @Override
    public void deleteAllEventCacheStartWithId(String memberId) {
        eventCache.forEach((key, emitter) -> {
            if(key.startsWith(memberId)) emitters.remove(key);
        });
    }
}
