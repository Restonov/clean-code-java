package com.epam.engx.cleancode.finaltask.task1.impl;

import com.epam.engx.cleancode.finaltask.task1.TableCreator;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.finaltask.task1.util.CalculatorUtil;
import com.epam.engx.cleancode.finaltask.task1.util.ConverterUtil;

import static com.epam.engx.cleancode.finaltask.task1.LevelBoundary.TABLE_UPPER;
import static com.epam.engx.cleancode.finaltask.task1.LevelBoundary.TABLE_MIDDLE;
import static com.epam.engx.cleancode.finaltask.task1.LevelBoundary.TABLE_BOTTOM;

import java.util.List;

public class DataTableCreator extends TableCreator {
    private static final int ONE_INDENT = 1;
    private static final int TWO_INDENTS = 2;
    private static final int THREE_INDENTS = 3;
    private static final int HALF_SIZE_DIVIDER = 2;
    private static final int ONE_ROW = 1;

    public DataTableCreator(List<DataSet> tableData) {
        super(tableData);
        initTable();
    }

    @Override
    public String create() {
        return createTableHeader(tableData) + createTableBody(tableData);
    }

    @Override
    protected void initTable() {
        columnsCount = countColumns(tableData);
        int cellTextSize = calculateCellTextSize(tableData);
        columnSize = calculateColumnSize(cellTextSize);
    }

    private int countColumns(List<DataSet> tableData) {
        return retrieveColumnNames(tableData).size();
    }

    private int calculateCellTextSize(List<DataSet> tableData) {
        int columnNameTextSize = calculateColumnNameTextSize(tableData);
        int cellValueTextSize = calculateCellValueTextSize(tableData);
        return Math.max(columnNameTextSize, cellValueTextSize);
    }

    private int calculateColumnNameTextSize(List<DataSet> tableData) {
        List<String> columnNames = tableData.get(FIRST_DATA_SET).getColumnNames();
        return CalculatorUtil.calculateTheLongestText(columnNames);
    }

    private int calculateCellValueTextSize(List<DataSet> tableData) {
        int valueTextMaxSize = 0;
        for (DataSet dataSet : tableData) {
            List<Object> cellValues = dataSet.getValues();
            List<String> texts = ConverterUtil.convertObjectsIntoStrings(cellValues);
            int valueTextSize = CalculatorUtil.calculateTheLongestText(texts);
            if (valueTextSize > valueTextMaxSize) {
                valueTextMaxSize = valueTextSize;
            }
        }
        return valueTextMaxSize;
    }

    private int calculateColumnSize(int cellTextSize) {
        return CalculatorUtil.isNumberEven(cellTextSize)
                ? cellTextSize + TWO_INDENTS
                : cellTextSize + THREE_INDENTS;
    }

    private String createTableHeader(List<DataSet> tableData) {
        StringBuilder header = new StringBuilder();
        header.append(createHorizontalRowBorder(TABLE_UPPER));
        List<String> columnNames = retrieveColumnNames(tableData);
        for (int columnNo = 0; columnNo < columnsCount; columnNo++) {
            header
                    .append(VERTICAL_BORDER)
                    .append(createTextWithIndents(columnNo, columnNames));
        }
        header
                .append(VERTICAL_BORDER)
                .append(ROW_LINE_BREAK)
                .append(createMiddleLevelBoundary());
        return header.toString();
    }

    private List<String> retrieveColumnNames(List<DataSet> tableData) {
        return tableData.get(FIRST_DATA_SET).getColumnNames();
    }

    private String createTableBody(List<DataSet> tableData) {
        int rowsCount = countRows(tableData);
        StringBuilder body = new StringBuilder();
        for (int row = 0; row < rowsCount; row++) {
            List<Object> values = tableData.get(row).getValues();
            body.append(VERTICAL_BORDER);
            for (int columnNo = 0; columnNo < columnsCount; columnNo++) {
                body
                        .append(createTextWithIndents(columnNo, ConverterUtil.convertObjectsIntoStrings(values)))
                        .append(VERTICAL_BORDER);
            }
            body.append(ROW_LINE_BREAK);
            if (row < rowsCount - ONE_ROW) {
                body.append(createMiddleLevelBoundary());
            }
        }
        body.append(createHorizontalRowBorder(TABLE_BOTTOM));
        return body.toString();
    }

    private int countRows(List<DataSet> tableData) {
        return tableData.size();
    }

    private String createTextWithIndents(int cellTextIndex, List<String> rowText) {
        StringBuilder textWithIndents = new StringBuilder();
        int cellTextSize = retrieveCellText(cellTextIndex, rowText).length();
        textWithIndents.append(createIndentation(columnSize, cellTextSize))
                .append(retrieveCellText(cellTextIndex, rowText))
                .append(createIndentationAfterText(columnSize, cellTextSize));
        return textWithIndents.toString();
    }

    private String retrieveCellText(int cellTextIndex, List<String> rowText) {
        return rowText.get(cellTextIndex);
    }

    private String createIndentation(int columnSize, int textLength) {
        return duplicateSymbol(TABLE_EMPTY_SPACE, (columnSize - textLength) / HALF_SIZE_DIVIDER);
    }

    private String createIndentationAfterText(int columnSize, int textLength) {
        StringBuilder indentation = new StringBuilder();
        if (CalculatorUtil.isNumberEven(textLength)) {
            indentation.append(createIndentation(columnSize, textLength));
        } else {
            indentation.append(duplicateSymbol(TABLE_EMPTY_SPACE,
                    ((columnSize - textLength) / HALF_SIZE_DIVIDER) + ONE_INDENT));
        }
        return indentation.toString();
    }

    private String createMiddleLevelBoundary() {
        return createHorizontalRowBorder(TABLE_MIDDLE);
    }
}
