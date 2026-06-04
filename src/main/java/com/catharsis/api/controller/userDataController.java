package com.catharsis.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

import com.catharsis.api.model.*;
import com.catharsis.api.service.*;


@RestController
@RequestMapping("/api")
public class userDataController {

    private final userDataService userDataService;

    public userDataController(userDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/userData")
    public ResponseEntity<?> save(@RequestBody userData userData) {
        userDataService.saveUserData(userData);
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/healthCheck")
    public String test() {
        return "API is running";
    }

}