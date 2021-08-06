package com.company;

import com.company.task1.VoidThreadsController;
import com.company.task2.PrimeNumberController;

public class MainMultithreadingPart1 {
    public static void main(String[] args) {
        System.out.println("Start of Task 1 \n");
        new VoidThreadsController().runTask1();
        System.out.println("\n\nStart of Task 2 \n");
        new PrimeNumberController().run();
    }
}
