package com.mrudul.mlmonitoringplatform.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double cpu;
    private double memory;
    private double latency;
    private double errorRate;
        private LocalDateTime timestamp;
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }
        public double getCpu() {
            return cpu;
        }
        public void setCpu(double cpu) {
            this.cpu = cpu;
        }
        public double getMemory() {
            return memory;
        }
        public void setMemory(double memory) {
            this.memory = memory;
        }
        public double getLatency() {
            return latency;
        }
        public void setLatency(double latency) {
            this.latency = latency;
        }
        public double getErrorRate() {
            return errorRate;
        }
        public void setErrorRate(double errorRate) {
            this.errorRate = errorRate;
        }
    
    // Add the existing fields and methods here
    
    
    
    public void setTimestamp(LocalDateTime timestamp) {
    
        this.timestamp = timestamp;

}


    


}
