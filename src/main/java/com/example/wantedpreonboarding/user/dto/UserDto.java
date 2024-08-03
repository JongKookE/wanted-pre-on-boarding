package com.example.wantedpreonboarding.user.dto;

import com.example.wantedpreonboarding.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private long userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhoneNumber;

    public UserEntity toEntity(UserDto userDto){
        return UserEntity.builder()
                .userId(userDto.getUserId())
                .userEmail(userDto.getUserEmail())
                .userPassword(userDto.getUserPassword())
                .userName(userDto.getUserName())
                .userPhoneNumber(userDto.getUserPhoneNumber())
            .build();
    }
}
