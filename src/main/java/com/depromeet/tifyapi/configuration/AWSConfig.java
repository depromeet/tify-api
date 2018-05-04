package com.depromeet.tifyapi.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    @Value("${aws.accessKey}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.endpoint}")
    private String endpoint;

    @Bean
    AmazonS3 amazonS3() {
        AmazonS3 amazonS3 = new AmazonS3Client(new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return accessKey;
            }

            @Override
            public String getAWSSecretKey() {
                return secretKey;
            }
        });
        amazonS3.setEndpoint(endpoint);
        return amazonS3;
    }

}
