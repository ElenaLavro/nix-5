package com.company;

import com.company.mapper.CSVTable;
import com.company.util.CSVMapper;
import com.company.util.CSVParser;

import java.util.Arrays;
import java.util.List;

public class Mapper {
    public static void main(String[] args) {
        List<CSVTable> csvTableList = CSVMapper.mapper(CSVTable.class);
        for (CSVTable table : csvTableList) {
            System.out.println(table);
        }
        CSVTable csvTable = CSVParser.readFromFile();
        System.out.println(Arrays.toString(csvTable.getHeaders()));
        System.out.println(csvTable.getValue(1, "Title"));
        System.out.println(csvTable.getValue(2, 1));


    }
}
