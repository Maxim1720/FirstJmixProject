package com.company.firstjmixproject.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;
import io.micrometer.core.lang.NonNullApi;

import javax.annotation.Nullable;


public enum OperationType implements EnumClass<String> {

    ENROLLMENT("enrollment"),
    WITHDRAWAL("withdrawal");

    private String id;

    OperationType(String value) {
        this.id = value;
    }
    public String getId() {
        return id;
    }

    @Nullable
    public static OperationType fromId(String id) {
        for (OperationType at : OperationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}