package com.rdk.rekognition.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.rdk.rekognition.service.ReKognitionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImageController(val service: ReKognitionService) {

    @GetMapping("/")
    fun index(): String {
        val faceDetails = service.detectFaces()

        val objectMapper = ObjectMapper()

        val result = StringBuilder()
        for (face in faceDetails) {
            result.append(objectMapper.writeValueAsString(face))
        }

        return result.toString()

    }


}