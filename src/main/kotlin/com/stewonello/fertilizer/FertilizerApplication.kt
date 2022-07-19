package com.stewonello.fertilizer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FertilizerApplication

fun main(args: Array<String>) {
	runApplication<FertilizerApplication>(*args)
}
