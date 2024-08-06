package com.example.wantedpreonboarding.notification.controller;

import com.example.wantedpreonboarding.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    // produces= "text/event-stream"을 해야함, MediaType의 상수는 "text/event-stream" 으로 이미 지정되어있음
    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@AuthenticationPrincipal User user,
                                                @RequestHeader(value= "Last-Event-ID", required = false, defaultValue = "")
                                                String lastEventId) {
        return ResponseEntity.ok(notificationService.subscribe(user.getUsername(), lastEventId));
    }
}
