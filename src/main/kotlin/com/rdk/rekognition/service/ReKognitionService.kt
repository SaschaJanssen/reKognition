package com.rdk.rekognition.service

import com.amazonaws.services.rekognition.model.FaceDetail
import com.amazonaws.services.rekognition.model.Label
import com.amazonaws.util.Base64
import org.springframework.stereotype.Service
import java.nio.ByteBuffer

@Service
class ReKognitionService(val faceDetector: FaceDetector, val labelDetector: LabelDetector) {


    private val base64canvasImageRegex = "^data:image/(png|jpeg);base64,".toRegex()

    fun detectFaces(imageData: String): List<FaceDetail> {
        val wrappedBytes = transformToByteBuffer(imageData)
        return faceDetector.detect(AwsClient.rekognitionClient, wrappedBytes)
    }

    fun detectLables(imageData: String): List<Label> {
        val wrappedBytes = transformToByteBuffer(imageData)
        return labelDetector.detect(AwsClient.rekognitionClient, wrappedBytes)
    }

    private fun transformToByteBuffer(imageData: String): ByteBuffer {
        val cleanedImageData = imageData.replace(base64canvasImageRegex, "")
        val imageBytes = Base64.decode(cleanedImageData)

        // FileOutputStream("img.jpeg").use { out -> out.write(imageBytes) }

        return ByteBuffer.wrap(imageBytes)
    }

}