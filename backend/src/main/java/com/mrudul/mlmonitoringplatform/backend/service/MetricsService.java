package com.mrudul.mlmonitoringplatform.backend.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final WriteApiBlocking writeApi;

    @Value("${influx.bucket}")
    private String bucket;

    @Value("${influx.org}")
    private String org;

    public MetricsService(InfluxDBClient influxDBClient) {
        this.writeApi = influxDBClient.getWriteApiBlocking();
    }

    public void writeMetrics(double cpu, double memory, double latency, double errorRate) {

        Point point = Point
                .measurement("system_metrics")
                .addTag("service", "game-simulator")
                .addField("cpu", cpu)
                .addField("memory", memory)
                .addField("latency", latency)
                .addField("errorRate", errorRate)
                .time(System.currentTimeMillis(), WritePrecision.MS);

        writeApi.writePoint(bucket, org, point);
        System.out.println("Writing metrics → CPU: " + cpu + ", Memory: " + memory);
        System.out.println("Bucket: " + bucket + ", Org: " + org);

    }
}
