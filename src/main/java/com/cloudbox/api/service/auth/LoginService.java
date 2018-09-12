package com.cloudbox.api.service.auth;

import com.cloudbox.api.domain.request.LoginRequest;
import com.cloudbox.api.service.s3.S3CredentialsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    @NotNull
    private S3CredentialsService s3CredentialsService;

    public void login(LoginRequest loginRequest) {
        Optional.of(s3CredentialsService.isExist(loginRequest.getAccessKey(), loginRequest.getSecretKey()))
                .filter(i -> i)
                .orElseGet(() -> {
                    register(loginRequest.getAccessKey(), loginRequest.getSecretKey());
                    return true;
                });
    }

    public void register(String accessKey, String secretKey) {
        s3CredentialsService.addCredentials(accessKey, secretKey)
                .subscribe(i -> log.info("register account: {} is succesfull", i),
                        i -> log.info("register account: {} is not succesfull", i));
    }
}
