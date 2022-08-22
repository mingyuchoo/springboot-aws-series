package com.s3.preSignedUrl.service;


import org.springframework.stereotype.Service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import java.net.URL;

@Service
public class PreSignedServiceImpl implements PreSignedService {
    @Override
    public URL get() {
        Regions clientRegion = Regions.AP_NORTHEAST_2;
        String bucketName = "my-bucket-name";
        String objectKey = "my-object-key";
        long duration = 1000 * 60;

        URL urlGET = null;
        try {

            ProfileCredentialsProvider profile = new ProfileCredentialsProvider();

            AmazonS3 s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(profile)
                    .withRegion(clientRegion)
                    .build();

            // Set the pre-signed URL to expire.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += duration;
            expiration.setTime(expTimeMillis);


            GeneratePresignedUrlRequest generatePreSignedUrlRequestGET = new GeneratePresignedUrlRequest(bucketName, objectKey)
                    .withMethod(HttpMethod.GET)
                    .withExpiration(expiration);

            urlGET = s3Client.generatePresignedUrl(generatePreSignedUrlRequestGET);

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }
        return urlGET;
    }
}
