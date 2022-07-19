package com.stewonello.fertilizer.config

import com.stewonello.fertilizer.dialect.FertilizerDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun fertilizerDialect(): FertilizerDialect {
        return FertilizerDialect("com.stewonello.fertilizer.components")
    }
}