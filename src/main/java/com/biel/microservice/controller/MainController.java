package com.biel.microservice.controller;

import com.biel.microservice.model.AnalyzedUrl;
import com.biel.microservice.model.TaskDTO;
import com.biel.microservice.model.User;
import com.biel.microservice.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {


    private ApplicationService service;

    @Autowired
    public MainController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping("/api/add")
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO){
        User user = service.authenticationUser(taskDTO.getUsername(), taskDTO.getPassword());
        service.taskRunner(user.getUuid(),taskDTO.getUrl());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/{uuid}")
    public List<AnalyzedUrl> findAnalyzedUrl(@PathVariable String uuid){
        return service.findAll(uuid);
    }
    @GetMapping("/api/internal/{id}")
    public List<String> findInternalLinks(@PathVariable Long id){
        return service.findInternalLinks(id);
    }




}
