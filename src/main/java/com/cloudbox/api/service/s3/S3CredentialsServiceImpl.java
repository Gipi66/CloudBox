package com.cloudbox.api.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.cloudbox.api.domain.mongo.amazon.S3Credentials;
import com.cloudbox.api.exception.cloud.auth.CloudAuthValidationException;
import com.cloudbox.api.mongo.repository.amazon.S3CredentialsRepository;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class S3CredentialsServiceImpl implements S3CredentialsService {

    @Autowired
    private Function<S3Credentials, AmazonS3> s3DynamicClient;


    @NotNull
    private S3CredentialsRepository s3CredentialsRepository;

    @Override
    public boolean isExist(String accessKey, String secretKey) {
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
    public Maybe<S3Credentials> addCredentials(String accessKey, String secretKey) {
        return Single.just(new S3Credentials(accessKey, secretKey))
                .flatMap(i -> s3CredentialsRepository.save(i))
                .filter(Objects::nonNull);
    }
}
