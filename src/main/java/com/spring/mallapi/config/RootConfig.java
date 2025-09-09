package com.spring.mallapi.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

    @Bean
    ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();

//      필드 매칭을 활성화 / 필드 접근 수준을 private으로 설정 / 매칭 전략을 느슨하게
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

}
