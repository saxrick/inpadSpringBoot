package com.inpad.spring.inpadspringboot.controller;
import com.inpad.spring.inpadspringboot.entity.CoefficientNormative;
import com.inpad.spring.inpadspringboot.service.CoefficientNormativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/coefficientnormative")
public class CoefficientNormativeRESTController {

    @Autowired
    CoefficientNormativeService coefficientNormativeService;

    @GetMapping("/{id}")
    public CoefficientNormative getCoefficientNormative(@PathVariable int id){
        return coefficientNormativeService.getCoefficientNormative(id);
    }

    @PostMapping("/")
    public ResponseEntity<CoefficientNormative> addNewCoefficientNormative(@RequestBody CoefficientNormative coefficientNormative){
        CoefficientNormative createdCoefficientNormative = coefficientNormativeService.saveCoefficientNormative(coefficientNormative);
        return ResponseEntity.created(URI.create("/coefficientNormative/" + createdCoefficientNormative.getId()))
                .body(createdCoefficientNormative);
    }

    @PutMapping("/{id}")
    public void updateCoefficientNormative(@RequestBody CoefficientNormative coefficientNormative, @PathVariable int id){
        coefficientNormativeService.saveUpdatedCoefficientNormative(coefficientNormative, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCoefficientNormative(@PathVariable int id){
        coefficientNormativeService.deleteCoefficientNormative(id);
        return "normative coefficient with id = " + id + " was deleted";
    }
}
