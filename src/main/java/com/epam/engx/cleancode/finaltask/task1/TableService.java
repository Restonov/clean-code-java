package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.impl.DataTableCreator;
import com.epam.engx.cleancode.finaltask.task1.impl.EmptyTableCreator;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DataSetImpl;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.DatabaseManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TableService {
    private static final String TABLE_NAME = "tableName";

    private final DatabaseManager manager;

    public String createTable(String tableName) {
        List<DataSet> tableData = manager.getTableData(tableName);
        return tableData.isEmpty()
                ? new EmptyTableCreator(prepareEmptyTableData(tableName)).create()
                : new DataTableCreator(tableData).create();
    }

    private List<DataSet> prepareEmptyTableData(String tableName) {
        List<DataSet> emptyTableData = new ArrayList<>();
        DataSet dataSet = new DataSetImpl();
        dataSet.put(TABLE_NAME, tableName);
        emptyTableData.add(dataSet);
        return emptyTableData;
    }
}
