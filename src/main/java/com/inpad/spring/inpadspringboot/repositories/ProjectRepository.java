package com.inpad.spring.inpadspringboot.repositories;

import com.inpad.spring.inpadspringboot.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
