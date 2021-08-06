package com.company.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimeNumberController {
    public void run() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            numbers.add(random.nextInt(99));
            System.out.println("Number is : " + numbers.get(i));
        }

        MyThread myThread = new MyThread(numbers);
        myThread.start();
    }

}
