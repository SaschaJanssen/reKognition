package com.rdk.rekognition

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ImageReKognitionApp

fun main(args: Array<String>) {
    SpringApplication.run(ImageReKognitionApp::class.java, *args)
}
