package com.example.wantedpreonboarding.notification.entity;

import com.example.wantedpreonboarding.global.entity.BaseTimeEntity;
import com.example.wantedpreonboarding.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Entity(name = "notify")
public class NotificationEntity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notifyId;

    @Column
    private String notifyContent;

    @Column(nullable = false)
    private boolean isRead;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @Builder
    public NotificationEntity(String notifyContent, boolean isRead, NotificationType notificationType, UserEntity userEntity,
                              LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(createdAt, updatedAt, deletedAt);
        this.notifyContent = notifyContent;
        this.isRead = isRead;
        this.notificationType = notificationType;
        this.userEntity = userEntity;
    }


    public enum NotificationType{
        NINEDAY, TENDAY, CHAT
    }

}

