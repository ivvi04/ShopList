package ru.lakeevda.authservice.entity.enums;

import java.util.Arrays;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public UserRole fromValue(String text) {
        return Arrays.stream(UserRole.values())
                .filter(candidate -> candidate.value.equals(text))
                .findFirst()
                .orElse(null);
    }
}
