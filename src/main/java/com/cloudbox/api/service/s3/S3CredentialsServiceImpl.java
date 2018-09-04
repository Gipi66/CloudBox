package com.cloudbox.api.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.cloudbox.api.domain.mongo.amazon.S3Credentials;
import com.cloudbox.api.domain.response.ErrorRS;
import com.cloudbox.api.exception.cloud.auth.CloudAuthValidationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class S3CredentialsServiceImpl implements S3CredentialsService {

    @NonNull
    private Function<S3Credentials, AmazonS3> s3DynamicClient;

    @Override
    public boolean checkCredentials(String accessKey, String secretKey) {
        return Optional.of(new S3Credentials(accessKey, secretKey))
                .map(this::initDynamicClient)
                .map(AmazonS3::getS3AccountOwner)
                .filter(Objects::nonNull)
                .filter(i -> i.getId() != null)
                .filter(i -> i.getDisplayName() != null)
                .map(ignore -> true)
                .orElseThrow(CloudAuthValidationException::new);
    }

    private AmazonS3 initDynamicClient(S3Credentials s3Credentials) {
        return s3DynamicClient.apply(s3Credentials);
    }

    @Override
    public S3Credentials addCredentials(String accessKey, String secretKey) {
        throw new UnsupportedOperationException("not implemented!");
    }
}
