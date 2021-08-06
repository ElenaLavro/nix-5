package com.company.task2;

import java.util.List;

public class MyThread extends Thread {
    private final List<Integer> integerList;
    public int result = 0;

    public MyThread(List<Integer> integerList) {
        this.integerList = integerList;
    }

    @Override
    public void run() {
        for (Integer integer : integerList) {
            result += isPrime(integer);
        }
        System.out.println("Result is " + result);
    }

    private int isPrime(int number) {
        int j = 2;
        boolean isPrime = true;
        while (j < number / 2) {
            if (number % j == 0) {
                isPrime = false;
                break;
            }
            j++;
        }
        return isPrime ? 1 : 0;
    }
}
