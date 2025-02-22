package com.itcube.journal.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, TEACHER, METHODIST;

    @Override
    public String getAuthority() {
        return name();
    }
}
