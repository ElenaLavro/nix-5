package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Horse extends Thread {
    private final Integer id;
    private final AtomicInteger place;
    private final ConcurrentHashMap<Integer, Integer> raceResults;
    private final CyclicBarrier cyclicBarrier;
    private final int minDistance = 100;
    private final int minSleep = 400;
    private final int difference = 100;
    private final int fullDistance = 1000;
    private final CountDownLatch countDownLatch;
    private int distance = 0;


    public Horse(Integer id, AtomicInteger place, ConcurrentHashMap<Integer, Integer> raceResults, CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
        this.id = id;
        this.place = place;
        this.raceResults = raceResults;
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            while (distance < fullDistance) {
                distance += (minDistance + (Math.random() * difference));
                Thread.sleep((long) (minSleep + (Math.random() * difference)));
            }
            raceResults.put(id, place.incrementAndGet());
            countDownLatch.countDown();
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
