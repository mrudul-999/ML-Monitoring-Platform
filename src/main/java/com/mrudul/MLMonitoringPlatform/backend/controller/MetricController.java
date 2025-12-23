package com.mrudul.MLMonitoringPlatform.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrudul.MLMonitoringPlatform.backend.dto.MetricRequest;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    @PostMapping
    public ResponseEntity<String> recieveMetrics(@RequestBody MetricRequest metric)
    {
        System.out.println("Metrics Recieved: ");
        System.out.println("CPU: " +  metric.getCpu());
        System.out.println("memory" + metric.getMemory());
        System.out.println("latency" + metric.getLatency());
        System.out.println("error" + metric.getErrorRate());

        return ResponseEntity.ok("Metrics Recieved");
        
    }
    
}
