package com.rdk.rekognition.service

import com.amazonaws.AmazonClientException
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.rekognition.AmazonRekognition
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder

class AwsClient {

    companion object {
        val credentials: AWSCredentials = awsCredentials()
        val rekognitionClient = buildAwsRekognitionClient(credentials)

        private fun buildAwsRekognitionClient(credentials: AWSCredentials): AmazonRekognition {
            return AmazonRekognitionClientBuilder
                    .standard()
                    .withRegion(Regions.EU_WEST_1)
                    .withCredentials(AWSStaticCredentialsProvider(credentials))
                    .build()
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

}