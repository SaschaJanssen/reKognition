package com.rdk.rekognition.controller

import com.amazonaws.services.rekognition.model.FaceDetail
import com.rdk.rekognition.service.ReKognitionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/analyse")
class AnalyseController(val service: ReKognitionService) {

    @PostMapping("/webcam")
    fun analyseWebCam(@RequestParam(value = "imageData") imageData: String): List<FaceDetail> {
        return service.detectFaces(imageData)
    }


}