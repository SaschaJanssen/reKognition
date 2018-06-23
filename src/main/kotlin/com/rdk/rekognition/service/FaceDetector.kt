package com.rdk.rekognition.service

import com.amazonaws.services.rekognition.AmazonRekognition
import com.amazonaws.services.rekognition.model.Attribute
import com.amazonaws.services.rekognition.model.DetectFacesRequest
import com.amazonaws.services.rekognition.model.FaceDetail
import com.amazonaws.services.rekognition.model.Image
import org.springframework.stereotype.Service
import java.nio.ByteBuffer

@Service
class FaceDetector {

    fun detect(client: AmazonRekognition, imageBytes: ByteBuffer): List<FaceDetail> {
        val requestDetectFace = DetectFacesRequest()
                .withImage(Image().withBytes(imageBytes))
                .withAttributes(Attribute.ALL)

        val resultDetectFace = client.detectFaces(requestDetectFace)
        return resultDetectFace.faceDetails
    }

}