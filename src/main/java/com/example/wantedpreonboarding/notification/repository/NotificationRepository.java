package com.example.wantedpreonboarding.notification.repository;

import com.example.wantedpreonboarding.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
