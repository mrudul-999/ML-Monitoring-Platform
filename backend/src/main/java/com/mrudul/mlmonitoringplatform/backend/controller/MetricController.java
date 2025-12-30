package com.mrudul.mlmonitoringplatform.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrudul.mlmonitoringplatform.backend.dto.MetricRequest;
import com.mrudul.mlmonitoringplatform.backend.repository.MetricRepository; // Ensure this path is correct
import com.mrudul.mlmonitoringplatform.backend.entity.Metric;
import java.time.LocalDateTime; // Import LocalDateTime

@RestController
@RequestMapping("/metrics")
public class MetricController {

    private final MetricRepository metricRepository; // Use camelCase for variable names

    public MetricController(MetricRepository metricRepository)
    {
        this.metricRepository = metricRepository;
    }

    @PostMapping
    public ResponseEntity<String> recieveMetrics(@RequestBody MetricRequest metricRequest) // Correct parameter name
    {

       
        Metric newMetric = new Metric();
        newMetric.setCpu(metricRequest.getCpu());
        newMetric.setMemory(metricRequest.getMemory());
        newMetric.setLatency(metricRequest.getLatency());
        newMetric.setErrorRate(metricRequest.getErrorRate());
        newMetric.setTimestamp(LocalDateTime.now());

        this.metricRepository.save(newMetric); // Use 'this' to refer to the instance variable

        return ResponseEntity.ok("Metrics Recieved");
        
    }
    
}
