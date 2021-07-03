package com.company.task1.controller;

import com.company.task1.service.ListOfDateService;

public class ListOfDateController {
    public void run() {
        ListOfDateService date = new ListOfDateService();
        System.out.println("Start reading file ~~~");
        date.writingOutputFile(date.readingInputFile());
        System.out.println("Dates have been successfully read and written to the file\n");
    }
}
