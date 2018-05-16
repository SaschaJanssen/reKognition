package com.rdk.rekognition.controller

import com.amazonaws.services.rekognition.model.FaceDetail
import com.rdk.rekognition.service.ReKognitionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImageController(val service: ReKognitionService) {

    @GetMapping("/imageinfo")
    fun index(): List<FaceDetail> {
        return service.detectFaces()
    }


}