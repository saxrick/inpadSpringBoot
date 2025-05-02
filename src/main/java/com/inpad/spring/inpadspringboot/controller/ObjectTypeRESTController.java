package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.entity.ObjectType;
import com.inpad.spring.inpadspringboot.service.ObjectTypeService;
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
@RequestMapping("/objecttype")
public class ObjectTypeRESTController {

    @Autowired
    ObjectTypeService objectTypeService;

    @GetMapping("/all")
    public List<ObjectType> getAllObjectTypes(){
        return objectTypeService.getAllObjectTypes();
    }

}
