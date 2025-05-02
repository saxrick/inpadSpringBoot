package com.inpad.spring.inpadspringboot.service;

import com.inpad.spring.inpadspringboot.entity.ObjectType;
import com.inpad.spring.inpadspringboot.repositories.ObjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectTypeServiceImpl implements ObjectTypeService{

    @Autowired
    private final ObjectTypeRepository objectTypeRepository;

    @Override
    public List<ObjectType> getAllObjectTypes() {
        return objectTypeRepository.findAll();
    }
}
