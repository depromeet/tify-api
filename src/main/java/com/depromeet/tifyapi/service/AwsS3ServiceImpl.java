package com.depromeet.tifyapi.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service("AwsS3Service")
@Slf4j
public class AwsS3ServiceImpl implements StorageService {
    private static final int READ_LIMIT = 1000000;
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.endpoint}")
    private String endpoint;

    @Override
    public void downloadObject(String url) {
        return;
    }

    @Override
    public String uploadObject(InputStream inputStream, String fileName) {
        try {
            String folderName = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "/";
            String ObjectKey = folderName + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, ObjectKey, inputStream, new ObjectMetadata());
            putObjectRequest.getRequestClientOptions().setReadLimit(READ_LIMIT);
            amazonS3.putObject(putObjectRequest);

            return "https://" + endpoint + "/" + bucketName + "/" + ObjectKey;
        } catch (AmazonServiceException ase) {
            log.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            log.error("Error Message:    " + ase.getMessage());
            log.error("HTTP Status Code: " + ase.getStatusCode());
            log.error("AWS Error Code:   " + ase.getErrorCode());
            log.error("Error Type:       " + ase.getErrorType());
            log.error("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
            throw ase;
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ace.getMessage());
            throw ace;
        }
    }
}
