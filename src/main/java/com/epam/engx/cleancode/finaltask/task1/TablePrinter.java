package com.epam.engx.cleancode.finaltask.task1;

import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.finaltask.task1.thirdpartyjar.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TablePrinter implements Command {
    private static final String COMMAND_START = "print ";
    private static final String COMMAND_SPLITTER = " ";
    private static final int COMMAND_PARAMETERS_AMOUNT = 2;
    private static final int COMMAND_TABLE_NAME = 1;

    private View view;
    private TableService tableService;

    @Override
    public boolean canProcess(String command) {
        return command.startsWith(COMMAND_START);
    }

    @Override
    public void process(String command) {
        String tableName = retrieveTableNameFromCommand(command);
        String table = tableService.createTable(tableName);
        view.write(table);
    }

    private String retrieveTableNameFromCommand(String command) {
        String[] commandParameters = command.split(COMMAND_SPLITTER);
        verifyCommandParametersNumber(commandParameters.length);
        return commandParameters[COMMAND_TABLE_NAME];
    }

    private void verifyCommandParametersNumber(int commandParametersNumber) {
        if (commandParametersNumber != COMMAND_PARAMETERS_AMOUNT) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (commandParametersNumber - 1));
        }
    }
}
