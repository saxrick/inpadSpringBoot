package com.inpad.spring.inpadspringboot.repositories;

import com.inpad.spring.inpadspringboot.entity.CoefficientNormative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoefficientNormativeRepository extends JpaRepository<CoefficientNormative, Integer> {
    CoefficientNormative findByModelId(String modelId);
}
