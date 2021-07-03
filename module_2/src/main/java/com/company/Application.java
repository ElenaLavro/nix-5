package com.company;

import com.company.task1.controller.ListOfDateController;
import com.company.task2.controller.UniqueNameController;
import com.company.task3.controller.TheMostProfitableWayController;

import java.util.Scanner;

public class Application {
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please, enter the number of task you want to do");
            System.out.println("1. Date list");
            System.out.println("2. List of names");
            System.out.println("3. List of cities");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            switch (sc.nextInt()) {
                case 0 -> {
                    System.out.println("~Goodbye~");
                    System.exit(0);
                }
                case 1 -> {
                    ListOfDateController controller = new ListOfDateController();
                    controller.run();
                    System.out.println();
                }
                case 2 -> {
                    UniqueNameController controller = new UniqueNameController();
                    controller.run();
                    System.out.println();
                }
                case 3 -> {
                    TheMostProfitableWayController controller = new TheMostProfitableWayController();
                    controller.run();
                    System.out.println();
                }
                default -> {
                    System.out.println("Wrong data. Please check");
                    System.out.println();
                }
            }
        }
    }
}

