package com.rdk.rekognition.service

import com.amazonaws.services.rekognition.model.FaceDetail
import com.amazonaws.util.Base64
import org.springframework.stereotype.Service
import java.io.FileOutputStream
import java.nio.ByteBuffer

@Service
class ReKognitionService(val faceDetector: FaceDetector) {


    fun detectFaces(imageData: String): List<FaceDetail> {
        val imageBytes = Base64.decode(imageData)

        FileOutputStream("img.jpeg").use { out -> out.write(imageBytes) }

        val wrappedBytes = ByteBuffer.wrap(imageBytes)
        return faceDetector.detect(AwsClient.rekognitionClient, wrappedBytes)

    }

}