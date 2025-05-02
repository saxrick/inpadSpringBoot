package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.entity.ObjectSubType;
import com.inpad.spring.inpadspringboot.service.ObjectSubTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/objectsubtype")
public class ObjectSubTypeRESTController {
    @Autowired
    ObjectSubTypeService objectSubTypeService;

    @GetMapping("/all")
    public List<ObjectSubType> getAllObjectSubTypes(){
        return objectSubTypeService.getAllObjectSubTypes();
    }
}
