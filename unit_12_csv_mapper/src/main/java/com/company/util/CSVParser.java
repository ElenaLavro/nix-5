package com.company.util;

import com.company.mapper.CSVTable;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static CSVTable readFromFile() {
        List<String[]> data = new ArrayList<>();
        try (InputStream inputStreamReader = CSVParser.class.getClassLoader().getResourceAsStream("groups.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(inputStreamReader))) {
            data = reader.readAll();
        } catch (IOException | CsvException exception) {
            exception.printStackTrace();
        }
        return new CSVTable(data);
    }
}
