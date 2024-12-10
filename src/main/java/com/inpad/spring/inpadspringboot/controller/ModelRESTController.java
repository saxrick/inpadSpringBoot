package com.inpad.spring.inpadspringboot.controller;

import com.inpad.spring.inpadspringboot.dto.ModelDTO;

import com.inpad.spring.inpadspringboot.dto.SignUpModelDTO;

import com.inpad.spring.inpadspringboot.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelRESTController {

    Logger log = Logger.getLogger(ModelRESTController.class.getName());

    @Autowired
    ModelService modelService;

    @GetMapping("/all")
    public List<ModelDTO> getAllModels(){
        log.info(java.time.LocalDateTime.now() + " Запрошен список моделей");
        ModelDTO modelDTO = new ModelDTO();
        return modelDTO.getModelDTOList(modelService.getAllModels());
    }

    @GetMapping("/{id}")
    public ModelDTO getModel(@PathVariable int id){
        log.info(java.time.LocalDateTime.now() + " Запрошена модель с id " + id);
        ModelDTO modelDTO = new ModelDTO();
        ModelDTO gotModelDTO = modelDTO.getModelDTO(modelService.getModel(id));
        return gotModelDTO;
    }


    @PostMapping("/")
    public ResponseEntity<ModelDTO> addNewModel(@RequestBody SignUpModelDTO modelDTO){
        ModelDTO createdModel = modelService.saveModel(modelDTO);
        log.info(java.time.LocalDateTime.now() + " Создана модель с id " + createdModel.getId());
        return ResponseEntity.created(URI.create("/models/" + createdModel.getId()))
                .body(createdModel);
    }

    @PutMapping("/{id}")
    public void updateModel(@RequestBody SignUpModelDTO modelDTO, @PathVariable int id){
        modelService.saveUpdatedModel(modelDTO, id);
        log.info(java.time.LocalDateTime.now() + " Обновлена модель с id " + id);
    }

    @DeleteMapping("/{id}")
    public String deleteModel(@PathVariable int id){
        log.info(java.time.LocalDateTime.now() + " Удалена модель с id " + id);
        modelService.deleteModel(id);
        return "Model with id = " + id + " was deleted";
    }
}
