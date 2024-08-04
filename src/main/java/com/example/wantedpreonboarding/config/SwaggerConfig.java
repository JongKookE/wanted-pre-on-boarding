package com.example.wantedpreonboarding.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "원티드 프리 온 보딩",
                description = "포트폴리오 프로젝트",
                version = "v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

}
