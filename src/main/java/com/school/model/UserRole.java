package com.school.model;

public enum UserRole {
    ADMIN, USER, GUEST;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
