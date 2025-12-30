package com.mrudul.mlmonitoringplatform.backend;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Random;

public class MetricsSimulator {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws InterruptedException, IOException {
        Random random = new Random();

        while (true) {
            double cpu = 10 + random.nextDouble(70);
            double memory = 20 + random.nextDouble(70);
            double latency = 50 + random.nextDouble(450);
            double errorRate = random.nextDouble();

            String json = """
                    {
                        "cpu": %.2f,
                        "memory": %.2f,
                        "latency": %.2f,
                        "errorRate": %.2f,
                        "timestamp": "%s"
                    }
                           \s""".formatted(cpu, memory, latency, errorRate, Instant.now());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/metrics"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Sent metric → CPU: " + cpu + ", Memory: " + memory);

            Thread.sleep(5000);

        }
    }
}
