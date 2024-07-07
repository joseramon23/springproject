package com.example.api_package.commons.enums;

public enum PackageStatusEnum {
    SENT("sent"),
    IN_TRANSIT("in_transit"),
    DELIVERED("delivered");

    private final String value;

    PackageStatusEnum(String value) {
        this.value = value;
    }
}
