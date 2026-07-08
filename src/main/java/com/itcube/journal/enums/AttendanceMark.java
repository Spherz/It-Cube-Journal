package com.itcube.journal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AttendanceMark {
    PRESENT("Б"),
    ABSENT("Н");

    private final String code;

    AttendanceMark(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static AttendanceMark fromCode(String code) {
        for (AttendanceMark mark : values()) {
            if (mark.code.equals(code)) {
                return mark;
            }
        }
        throw new IllegalArgumentException("Unknown attendance mark: " + code);
    }
}
