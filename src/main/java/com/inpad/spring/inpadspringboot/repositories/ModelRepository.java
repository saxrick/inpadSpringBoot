package com.inpad.spring.inpadspringboot.repositories;

import com.inpad.spring.inpadspringboot.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository  extends JpaRepository<Model, Integer> {

}
