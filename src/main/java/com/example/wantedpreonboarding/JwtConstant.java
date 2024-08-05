package com.example.wantedpreonboarding;

import lombok.Getter;

@Getter
public enum JwtConstant {
    ACCESS("AccessToken"),
    REFRESH("RefreshToken"),
    USERID("UserId"),
    USERNAME("Username"),
    USEREMAIL("UserEmail");

    private final String constant;

    JwtConstant(String constant){
        this.constant = constant;
    }

}
