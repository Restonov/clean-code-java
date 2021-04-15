package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;

import java.util.List;

public abstract class TableCreator {
    protected static final String HORIZONTAL_BORDER_PART = "═";
    protected static final String VERTICAL_BORDER = "║";
    protected static final String TABLE_EMPTY_SPACE = " ";
    protected static final String ROW_LINE_BREAK = "\n";
    protected static final int FIRST_DATA_SET = 0;
    private static final int ONE_ACTUAL_COLUMN = 1;

    protected final List<DataSet> tableData;
    protected int columnsCount;
    protected int columnSize;

    protected TableCreator(List<DataSet> tableData) {
        this.tableData = tableData;
    }

    protected abstract String create();

    protected abstract void initTable();

    protected String createHorizontalRowBorder(LevelBoundary levelBoundary) {
        StringBuilder rowBorder = new StringBuilder(levelBoundary.getLeftBoundary());
        String middleBorderPart = duplicateSymbol(HORIZONTAL_BORDER_PART, columnSize) + levelBoundary.getMiddleBoundary();
        if (columnsCount > 1) {
            rowBorder.append(duplicateSymbol(middleBorderPart, columnsCount - ONE_ACTUAL_COLUMN));
        }
        rowBorder
                .append(duplicateSymbol(HORIZONTAL_BORDER_PART, columnSize))
                .append(levelBoundary.getRightBoundary())
                .append(ROW_LINE_BREAK);
        return rowBorder.toString();
    }

    protected String duplicateSymbol(String symbol, int times) {
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < times; i++) {
            border.append(symbol);
        }
        return border.toString();
    }
}
