package com.montebruni.sales

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SalesSystemApplication

fun main(args: Array<String>) {
	runApplication<SalesSystemApplication>(*args)
}
