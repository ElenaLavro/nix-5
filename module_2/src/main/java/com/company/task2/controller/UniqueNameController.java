package com.company.task2.controller;

import com.company.task2.service.UniqueNameService;

public class UniqueNameController {
    public void run() {
        UniqueNameService nameService = new UniqueNameService();
        System.out.println("The first unique name is: " + nameService.convertDateToList() + "\n");
    }
}
