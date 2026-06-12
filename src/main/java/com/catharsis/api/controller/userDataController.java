package com.catharsis.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

import com.catharsis.api.model.*;
import com.catharsis.api.service.*;
import com.catharsis.api.dto.*;


@RestController
@RequestMapping("/api")
public class userDataController {

    private final userDataService userDataService;

    public userDataController(userDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/newAcc")
    public ResponseEntity<?> createNewAccount(@RequestBody userData userData) {
        String username = userData.getUsername();
        if (!isValidUsername(username)) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println("Attempting to make a new account for " + userData.getUsername());
        String output = (userDataService.initializeNewAccount(username));
        System.out.println(output);
        return ResponseEntity.ok(output);
    }

    @PostMapping("/saveUserData")
    public ResponseEntity<?> save(@RequestBody userData userData) {
        userDataService.saveUserData(userData);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateEnemiesCounts")
    public ResponseEntity<?> updateEnemiesCounts(@RequestBody updateEnemiesRequest request) {

        String username = request.getUsername();
        enemiesKilled enemiesKilled = request.getEnemiesKilled();

        String output = userDataService.updateEnemiesKilledByMobDeltaAndUsername(username, enemiesKilled);

        return ResponseEntity.ok(output);
    }

        @PostMapping("/completedLevel")
    public ResponseEntity<?> updateLevel(@RequestBody userData userData) {
        String username = userData.getUsername();
        userDataService.updateLevel(username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/healthCheck")
    public String test() {
        return "API is running";
    }

    private boolean isValidUsername(String username) {
        if (username == null || username.endsWith("=")) {
            System.out.println("Input data of wrong format");
            return false;
        }
        return true;
    }

}