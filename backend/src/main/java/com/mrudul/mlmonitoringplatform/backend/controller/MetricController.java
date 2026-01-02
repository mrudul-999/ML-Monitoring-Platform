package com.mrudul.mlmonitoringplatform.backend.controller;

import com.mrudul.mlmonitoringplatform.backend.dto.MetricRequest;
import com.mrudul.mlmonitoringplatform.backend.service.MetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    private final MetricsService influxService;

    public MetricController(MetricsService influxService) {
        this.influxService = influxService;
    }

    @PostMapping
    public ResponseEntity<String> recieveMetrics(@RequestBody MetricRequest metricRequest) {
        influxService.writeMetrics(
                metricRequest.getCpu(),
                metricRequest.getMemory(),
                metricRequest.getLatency(),
                metricRequest.getErrorRate()
        );
        return ResponseEntity.ok("Metrics Received in InfluxDB");
    }
}
