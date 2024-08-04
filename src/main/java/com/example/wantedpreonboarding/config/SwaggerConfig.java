package com.example.wantedpreonboarding.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "시나브로의 API 명세서",
                description = "팀 HP50입니다.",
                version = "v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

}
