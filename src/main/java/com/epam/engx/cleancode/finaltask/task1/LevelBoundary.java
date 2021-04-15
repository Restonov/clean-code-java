package com.epam.engx.cleancode.finaltask.task1;

import lombok.Getter;

public enum LevelBoundary {
    TABLE_UPPER("╔", "╦", "╗"),
    TABLE_MIDDLE("╠", "╬", "╣"),
    TABLE_BOTTOM("╚", "╩", "╝"),
    EMPTY_TABLE_UPPER("╔", "", "╗"),
    EMPTY_TABLE_BOTTOM("╚", "", "╝");

    @Getter
    private final String leftBoundary;
    @Getter
    private final String middleBoundary;
    @Getter
    private final String rightBoundary;

    LevelBoundary(String leftBoundary, String middleBoundary, String rightBoundary) {
        this.leftBoundary = leftBoundary;
        this.middleBoundary = middleBoundary;
        this.rightBoundary = rightBoundary;
    }
}

