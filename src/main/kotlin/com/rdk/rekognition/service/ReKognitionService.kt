package com.rdk.rekognition.service

import com.amazonaws.services.rekognition.model.FaceDetail
import com.amazonaws.util.Base64
import org.springframework.stereotype.Service
import java.io.FileOutputStream
import java.nio.ByteBuffer

@Service
class ReKognitionService(val faceDetector: FaceDetector) {


    private val base64canvasImageRegex = "^data:image/(png|jpeg);base64,".toRegex()

    fun detectFaces(imageData: String): List<FaceDetail> {

        val cleanedImageData = imageData.replace(base64canvasImageRegex, "")

        val imageBytes = Base64.decode(cleanedImageData)

        FileOutputStream("img.jpeg").use { out -> out.write(imageBytes) }

        val wrappedBytes = ByteBuffer.wrap(imageBytes)
        return faceDetector.detect(AwsClient.rekognitionClient, wrappedBytes)

    }

}