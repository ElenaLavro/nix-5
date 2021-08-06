package com.company.task1;

public class VoidThreadsController {
    public void runTask1() {
        for (int i = 49; i >= 0; i--) {
            try {
                new VoidThreads(i + ")").start();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}
