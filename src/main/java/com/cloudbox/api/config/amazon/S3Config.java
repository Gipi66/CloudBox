package com.cloudbox.api.config.amazon;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.cloudbox.api.domain.mongo.amazon.S3Credentials;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@Configurable
public class S3Config {

    /**
     * Bean for dynamic client generation
     *
     * @return
     */
    @Bean
    public Function<S3Credentials, AmazonS3> s3DynamicClient() {
        return s3Credentials -> {
            AWSCredentialsProvider provider = new AWSStaticCredentialsProvider(s3Credentials);
            return AmazonS3Client
                    .builder()
                    .withCredentials(provider)
                    .build();
        };
    }
}
