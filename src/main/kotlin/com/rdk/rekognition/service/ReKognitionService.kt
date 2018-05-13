package com.rdk.rekognition.service

import com.amazonaws.AmazonClientException
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.rekognition.AmazonRekognition
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder
import com.amazonaws.services.rekognition.model.FaceDetail
import com.amazonaws.util.IOUtils
import org.springframework.stereotype.Service
import java.nio.ByteBuffer


@Service
class ReKognitionService(val faceDetector: FaceDetector) {

    fun detectFaces(): List<FaceDetail> {
        val photo = "static/kid.jpg"

        val credentials: AWSCredentials = awsCredentials()
        val imageBytes: ByteBuffer = readImageToByteBuffer(photo)

        val rekognitionClient = buildAwsRekognitionClient(credentials)

        return faceDetector.detect(rekognitionClient, imageBytes)

    }

    private fun buildAwsRekognitionClient(credentials: AWSCredentials): AmazonRekognition {
        return AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .build()
    }

    private fun readImageToByteBuffer(photo: String): ByteBuffer {
        val resource = this.javaClass.classLoader.getResourceAsStream(photo)
        return resource.use({ inputStream -> ByteBuffer.wrap(IOUtils.toByteArray(inputStream)) })
    }

    private fun awsCredentials(): AWSCredentials {
        val credentials: AWSCredentials
        try {
            credentials = ProfileCredentialsProvider("default").getCredentials()
        } catch (e: Exception) {
            throw AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location (/Usersuserid.aws/credentials), and is in a valid format.", e)
        }
        return credentials
    }

}