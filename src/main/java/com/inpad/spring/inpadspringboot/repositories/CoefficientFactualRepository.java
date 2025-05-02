package com.inpad.spring.inpadspringboot.repositories;

import com.inpad.spring.inpadspringboot.entity.CoefficientFactual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoefficientFactualRepository extends JpaRepository<CoefficientFactual, Integer> {
    CoefficientFactual findByModelId(String modelId);
}
