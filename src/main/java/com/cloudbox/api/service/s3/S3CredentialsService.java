package com.cloudbox.api.service.s3;

import com.cloudbox.api.domain.mongo.amazon.S3Credentials;
import io.reactivex.Maybe;

public interface S3CredentialsService {
    boolean isExist(String accessKey, String secretKey);

    Maybe<S3Credentials> addCredentials(String accessKey, String secretKey);
}
