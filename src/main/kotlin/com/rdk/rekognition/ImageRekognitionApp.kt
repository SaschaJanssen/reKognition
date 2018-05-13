package com.rdk.rekognition

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ImageRekognitionApp


fun main(args: Array<String>) {
    SpringApplication.run(ImageRekognitionApp::class.java, *args)
}
