package com.rdk.rekognition.service

import com.amazonaws.services.rekognition.AmazonRekognition
import com.amazonaws.services.rekognition.model.DetectLabelsRequest
import com.amazonaws.services.rekognition.model.Image
import com.amazonaws.services.rekognition.model.Label
import org.springframework.stereotype.Service
import java.nio.ByteBuffer

@Service
class LabelDetector {

    fun detect(client: AmazonRekognition, imageBytes: ByteBuffer): List<Label> {
        val request = DetectLabelsRequest()
                .withImage(Image().withBytes(imageBytes))
                .withMaxLabels(10)
                .withMinConfidence(77f)

        val result = client.detectLabels(request)
        return result.getLabels()
    }

}