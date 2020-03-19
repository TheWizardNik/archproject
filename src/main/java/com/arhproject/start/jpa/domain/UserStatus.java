package com.arhproject.start.jpa.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    STUDENT,
    ARCHITECTOR,
    TEACHER;

    @JsonValue
    public String getJsonValue() {
        return name().toLowerCase();
    }
}
