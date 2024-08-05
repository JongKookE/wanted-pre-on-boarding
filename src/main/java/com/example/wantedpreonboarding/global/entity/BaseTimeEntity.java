package com.example.wantedpreonboarding.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // 해당 entity를 상속받을 경우 해당 필드에 있는 값을 column으로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // 최초 생성 시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정된 시간
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 삭제된 시간
    @Column
    private LocalDateTime deletedAt;

}
