package com.rdk.rekognition

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ReKognitionApp

fun main(args: Array<String>) {
    SpringApplication.run(ReKognitionApp::class.java, *args)
}
