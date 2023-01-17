package com.itcube.journal.domain;

public enum Marks {
    ABSENT("Н");

    private final String TITLE;
    Marks(String title) {
        this.TITLE = title;
    }

    public String getTITLE() {
        return TITLE;
    }
}
