package com.company.task3.controller;

import com.company.task3.service.TheMostProfitableWayService;

public class TheMostProfitableWayController {
    public void run() {
        System.out.println("~Start searching the most profitable way~");
        TheMostProfitableWayService service = new TheMostProfitableWayService();
        service.SearchingTheWay();
        System.out.println("The result is written to a file");
    }
}
