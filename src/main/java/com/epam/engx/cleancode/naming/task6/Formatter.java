package com.epam.engx.cleancode.naming.task6;

public class Formatter {
    private static final String CONTENT_CORNER = "+";
    private static final String CONTENT_SIDE_BORDER = "|";
    private static final String CONTENT_EDGE_BORDER = "-";
    private static final String CONTENT_DIVIDER = " _ ";

    public String formatContent(String key, String value) {
        String content = key + CONTENT_DIVIDER + value;
        String edgeBorder = createSymbolRow(CONTENT_EDGE_BORDER, content.length());
        return CONTENT_CORNER + edgeBorder + CONTENT_CORNER + "\n"
                + CONTENT_SIDE_BORDER + content + CONTENT_SIDE_BORDER + "\n"
                + CONTENT_CORNER + edgeBorder + CONTENT_CORNER + "\n";
    }

    private String createSymbolRow(String symbol, int symbolAmount) {
        String symbolRow = "";
        for (int i = 0; i < symbolAmount; i++) {
            symbolRow += symbol;
        }
        return symbolRow;
    }
}
