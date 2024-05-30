package com.example.demae.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

    ADMIN(Authority.ADMIN),
    STORE(Authority.STORE),
    USER(Authority.USER);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String STORE = "ROLE_STORE";
        public static final String USER = "ROLE_USER";
    }
}
