package com.cloudbox.api.domain.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BaseRS {
    @JsonProperty("success")
    private final boolean success;

    @JsonCreator
    public BaseRS(final boolean success) {
        this.success = success;
    }
}
