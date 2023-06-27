package com.stewonello.fertilizer

import com.innoq.comptus.core.ComptusDialect
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FertilizerApplication {

    @Bean
    fun comptusDialect(): ComptusDialect = ComptusDialect("com.stewonello.fertilizer.components")
}

fun main(args: Array<String>) {
    runApplication<FertilizerApplication>(*args)
}
