package com.mrudul.backend;

import java.util.Random;

public class MetricsSimulator {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        while (true){
            double cpu = 10 + random.nextDouble(70);
            double memory = 20 + random.nextDouble(70);
            double latency = 50 + random.nextDouble(450);
            double errorRate = random.nextDouble();
            System.out.println("CPU: " + cpu + " Memory " + memory + " Latency " + latency +
                    " ErrorRate: " + errorRate);
            Thread.sleep(5000);

        }
    }
}
