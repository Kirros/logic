package com.logic.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents simple 2D table with
 */
public class Table<H, V> {
    private static final String VERTICAL_DELIMITER = "\n";
    private static final String HORIZONTAL_DELIMITER = "\t|\t";
    private int numberOfLines = 0;
    private List<TableColumn> columns = new ArrayList<>();

    public void addColumn(H header, List<V> values) {
        TableColumn newColumn = new TableColumn(header, values);

        if (columns.isEmpty()) {
            numberOfLines = values.size();
            columns.add(newColumn);
        }
        else {
            assert numberOfLines == values.size();
            columns.add(newColumn);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (TableColumn column : columns) {
            result.append(column.header.toString()).append(HORIZONTAL_DELIMITER);
        }

        result.append("\n");

        for (int i = 0; i < numberOfLines; i++) {
            for (TableColumn column : columns) {
                result.append(column.getLineAsString(i)).append(HORIZONTAL_DELIMITER);
            }
            result.append(VERTICAL_DELIMITER);
        }

        return result.toString();
    }

    private class TableColumn {
        private H header;
        private List<V> values;
        private int width;

        TableColumn(H header, List<V> values) {
            this.header = header;
            this.values = values;
            this.width = header.toString().length();
        }

        String getLineAsString(int index) {
            String value = values.get(index).toString();
            int padSize = width - value.length();

            if (padSize < 1)
                return value;

            return new String(new char[padSize]).replace("\0", " ") + value;
        }
    }
}
