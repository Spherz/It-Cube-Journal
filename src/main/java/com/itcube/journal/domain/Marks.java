package com.itcube.journal.domain;

public enum Marks {
    Absent("Н");

    private String title;
    Marks(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
