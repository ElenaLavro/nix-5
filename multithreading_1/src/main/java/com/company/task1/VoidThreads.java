package com.company.task1;

public class VoidThreads extends Thread {
    public VoidThreads(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Hello from thread(" + getName());
    }
}

