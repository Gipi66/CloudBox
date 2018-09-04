package com.cloudbox.api.domain.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Getter
@JsonPropertyOrder({"success", "errors"})
public class ErrorRS extends BaseRS {

    @JsonProperty("errors")
    private final List<Error> errors;

    public ErrorRS(@NotNull @JsonProperty("errors") final List<Error> errors) {
        super(false);
        this.errors = errors;
    }

    public ErrorRS(@NotNull final String code,
                   @NotNull final String description) {
        this(Collections.singletonList(new Error(code, description)));
    }

    @Getter
    @JsonPropertyOrder({"code", "description"})
    public static class Error {
        @JsonProperty("code")
        private final String code;

        @JsonProperty("description")
        private final String description;

        private Error(@NotNull @JsonProperty("code") final String code,
                      @NotNull @JsonProperty("description") final String description) {
            this.code = code;
            this.description = description;
        }
    }
}
