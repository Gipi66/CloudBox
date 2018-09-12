package com.cloudbox.api.domain.mongo.amazon;

import com.amazonaws.auth.AWSCredentials;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "AmazonAccount")
@Data
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
//@CompoundIndexes({
//        @CompoundIndex(name = "AmazonAccount_key_index", def = "{'accessKey' : 1, 'secretKey': 1}")
//})
public class S3Credentials implements AWSCredentials {

    @Id
    private String id;

//    @Indexed(unique = true)
    @Field(value = "accessKey")
    @NonNull
    private String accessKey;

//    @Indexed(unique = true)
    @Field(value = "secretKey")
    @NonNull
    private String secretKey;

    @Field(value = "registrationDate")
    private LocalDate registrationDate = LocalDate.now();

//    @Indexed(unique = true)
    @Field(value = "lastSuccessfulAuth")
    private LocalDate lastSuccessfulAuth = LocalDate.now();

    @Version
    private Long version;

    @Transient
    @Override
    public String getAWSAccessKeyId() {
        return getAccessKey();
    }

    @Transient
    @Override
    public String getAWSSecretKey() {
        return getSecretKey();
    }
}
