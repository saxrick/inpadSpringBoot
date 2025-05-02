package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.ObjectSubType;
import com.inpad.spring.inpadspringboot.repositories.ObjectSubTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectSubTypeServiceImpl implements ObjectSubTypeService{

    @Autowired
    private final ObjectSubTypeRepository objectSubTypeRepository;

    @Override
    public List<ObjectSubType> getAllObjectSubTypes() {
        return objectSubTypeRepository.findAll();
    }
}
