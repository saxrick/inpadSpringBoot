package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.entity.CoefficientFactual;
import com.inpad.spring.inpadspringboot.service.CoefficientFactualService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/coefficientfactual")
public class CoefficientFactualRESTController {

    @Autowired
    CoefficientFactualService coefficientFactualService;

    @GetMapping("/{id}")
    public CoefficientFactual getCoefficientFactual(@PathVariable String id){
        return coefficientFactualService.getCoefficientFactual(id);
    }

    @PostMapping("/")
    public ResponseEntity<CoefficientFactual> addNewCoefficientFactual(@RequestBody CoefficientFactual coefficientFactual){
        CoefficientFactual createdCoefficientFactual = coefficientFactualService.saveCoefficientFactual(coefficientFactual);
        return ResponseEntity.created(URI.create("/coefficientFactual/" + createdCoefficientFactual.getId()))
                .body(createdCoefficientFactual);
    }

    @PutMapping("/{id}")
    public void updateCoefficientFactual(@RequestBody CoefficientFactual coefficientFactual, @PathVariable String id){
        coefficientFactualService.saveUpdatedCoefficientFactual(coefficientFactual, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCoefficientFactual(@PathVariable int id){
        coefficientFactualService.deleteCoefficientFactual(id);
        return "factual coefficient with id = " + id + " was deleted";
    }
}
