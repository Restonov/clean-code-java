package com.epam.engx.cleancode.finaltask.task1.impl;

import com.epam.engx.cleancode.finaltask.task1.TableCreator;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;

import static com.epam.engx.cleancode.finaltask.task1.LevelBoundary.EMPTY_TABLE_BOTTOM;
import static com.epam.engx.cleancode.finaltask.task1.LevelBoundary.EMPTY_TABLE_UPPER;

import java.util.List;

public class EmptyTableCreator extends TableCreator {
    private static final String EMPTY_TABLE_TEXT = "║ Table '%s' is empty or does not exist ║";
    private static final int ONE_COLUMN = 1;
    private static final int EMPTY_TEXT_BORDERS_AMOUNT = 2;
    private static final int TABLE_NAME_VALUE = 0;

    private String tableText;

    public EmptyTableCreator(List<DataSet> tableData) {
        super(tableData);
        initTable();
    }

    @Override
    public String create() {
        return createHorizontalRowBorder(EMPTY_TABLE_UPPER) +
                tableText +
                ROW_LINE_BREAK +
                createHorizontalRowBorder(EMPTY_TABLE_BOTTOM);
    }

    @Override
    protected void initTable() {
        columnsCount = ONE_COLUMN;
        tableText = createTableText(tableData);
        columnSize = calculateColumnSize();
    }

    private String createTableText(List<DataSet> tableData) {
        return String.format(EMPTY_TABLE_TEXT, parseTableName(tableData));
    }

    private String parseTableName(List<DataSet> tableData) {
        return String.valueOf(tableData.get(FIRST_DATA_SET).getValues().get(TABLE_NAME_VALUE));
    }

    private int calculateColumnSize() {
        return tableText.length() - EMPTY_TEXT_BORDERS_AMOUNT;
    }
}
