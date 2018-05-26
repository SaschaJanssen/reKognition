package com.rdk.rekognition.controller

import com.amazonaws.services.rekognition.model.FaceDetail
import com.rdk.rekognition.service.ReKognitionService
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/analyse")
class AnalyseController(val service: ReKognitionService) {

    @GetMapping("/image")
    fun analyseImage(): List<FaceDetail> {
        return service.detectFaces("")
    }

    @PostMapping("/webcam")
    fun analyseWebCam(@RequestParam(value = "imageData") imageData: String): List<FaceDetail> {
        return service.detectFaces(imageData)
    }


}