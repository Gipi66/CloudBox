package com.cloudbox.api.domain.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String accessKey;
    private String secretKey;
}
