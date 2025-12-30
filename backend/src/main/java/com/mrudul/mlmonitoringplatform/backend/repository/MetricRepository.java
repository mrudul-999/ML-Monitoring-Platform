package com.mrudul.mlmonitoringplatform.backend.repository;

import com.mrudul.mlmonitoringplatform.backend.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
