package com.company.mapper;

import java.util.ArrayList;
import java.util.List;

public class CSVTable {
    private final List<String[]> data;

    public CSVTable(List<String[]> data) {
        this.data = data;
    }

    public CSVTable() {
        data = new ArrayList<>();
    }

    public String[] getHeaders() {
        return this.data.get(0);
    }

    public String getValue(int row, int column) {
        return this.data.get(row)[column];
    }

    public String getValue(int row, String column) {
        String[] header = getHeaders();
        int colIndex = -1;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(column)) {
                colIndex = i;
                break;
            }
        }
        if (colIndex == -1) return null;
        return this.data.get(row)[colIndex];
    }

    public int getSize() {
        return this.data.size();
    }
}


/*   Map<String, Integer> header;
    List<List<String>> rows = new ArrayList<>();

    public CSVTable(Map<String, Integer> header) {
        this.header = header;
    }

    public int getHeaderIndex(String headName) {
        return header.get(headName);
    }

    public List<String> getListOfHeader() {
        return new ArrayList<>(header.keySet());
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }

    public List<String> getRow(int i) {
        return rows.get(i);
    }

    public List<String> getColumn(int i) {
        List<String> values = new ArrayList<>();
        for (List<String> row : rows) {
            values.add(row.get(i));
        }
        return values;
    }

    public String getValueByNumber(int row, int column) {
        return rows.get(row).get(column);
    }

    public String getValueByHeader(int row, String column) {
        return rows.get(row).get(header.get(column));
    }

    public void addRow(List<String> newRow) {
        rows.add(newRow);
    }
*/