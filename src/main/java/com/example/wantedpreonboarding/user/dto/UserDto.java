package com.example.wantedpreonboarding.user.dto;

import com.example.wantedpreonboarding.user.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Email(message = "이메일 형식이 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수 기입 사항입니다.")
    private String userEmail;

    //  대소문자, 숫자 5개 이상, 특수문자 포함 2개 이상 검증
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=(?:.*\\d.*){5})(?=(?:.*[!@#$%^&*()_+\\-={}\\[\\]:\";'<>?,./].*){2}).*$",
            message = "대소문자, 숫자 5개 이상, 특수문자 포함 2개 이상 포함해야합니다.")
    private String userPassword;

    // 아이디 대소문자 및 한글 이름 검즘
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]+$",
        message = "대소문자 하나 이상 포함")
    private String userName;
    
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
        message = "숫자와 하이픈으로 구성되어야합니다.")
    // 숫자와 하이폰으로 구성된 형식 검증
    private String userPhoneNumber;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(this.getUserId())
                .userEmail(this.getUserEmail())
                .userPassword(this.getUserPassword())
                .userName(this.getUserName())
                .userPhoneNumber(this.getUserPhoneNumber())
            .build();
    }
}
