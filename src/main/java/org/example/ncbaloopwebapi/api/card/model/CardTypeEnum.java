package org.example.ncbaloopwebapi.api.card.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CardTypeEnum {
    VIRTUAL("virtual"),
    PHYSICAL("physical");
    private final String value;
    CardTypeEnum(String value) {
        this.value = value.toLowerCase();
    }
    public String getValue() {
        return value;
    }
}
