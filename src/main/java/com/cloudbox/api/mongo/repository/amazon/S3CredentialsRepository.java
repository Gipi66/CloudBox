package com.cloudbox.api.mongo.repository.amazon;

import com.cloudbox.api.model.mongo.amazon.S3Credentials;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3CredentialsRepository extends RxJava2CrudRepository<S3Credentials, String> {
}
