package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Hippodrome {
    private final static ConcurrentHashMap<Integer, Integer> raceResults = new ConcurrentHashMap<>();
    private final static int amountOfHorses = 10;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(amountOfHorses);
        CyclicBarrier barrier = new CyclicBarrier(amountOfHorses);
        AtomicInteger place = new AtomicInteger(0);
        for (int i = 0; i < amountOfHorses; i++) {
            new Thread(new Horse(i, place, raceResults, barrier, countDownLatch)).start();
        }
        int numberOfHorse = 0;
        try {
            System.out.println("Please, enter the number of the horse you think will win");
            numberOfHorse = Integer.parseInt(br.readLine()) - 1;
            if (numberOfHorse < 0 || numberOfHorse > amountOfHorses - 1) {
                throw new RuntimeException("Out of range. Please check data and repeat later");
            }
        } catch (IOException exception) {
            System.out.println("IOException error");
        }


        try {
            countDownLatch.await();
            int resultNumber = raceResults.get(numberOfHorse);
            System.out.println("Horse № " + (numberOfHorse + 1) + " finished by number " + resultNumber);
            if (resultNumber == 1) {
                System.out.println("Congratulations!!!");
            } else {
                System.out.println("Don't give up!!!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
