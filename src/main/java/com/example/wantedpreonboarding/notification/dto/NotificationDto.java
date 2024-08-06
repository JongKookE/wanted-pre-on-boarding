package com.example.wantedpreonboarding.notification.dto;

import com.example.wantedpreonboarding.notification.entity.NotificationEntity.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationDto {
    long id;
    String name;
    String content;
    NotificationType type;
    String createdAt;
}
