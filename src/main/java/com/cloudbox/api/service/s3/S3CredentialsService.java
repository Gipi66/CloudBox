package com.cloudbox.api.service.s3;

import com.cloudbox.api.domain.mongo.amazon.S3Credentials;

public interface S3CredentialsService {
    boolean checkCredentials(String accessKey, String secretKey);

    S3Credentials addCredentials(String accessKey, String secretKey);
}
