package com.catharsis.api.service;

import com.catharsis.api.model.userData;

import org.springframework.stereotype.Service;

import com.catharsis.api.model.enemiesKilled;

@Service
public interface userDataService {

    userData getUserData(String username);

    String saveUserData(userData userData);

    String initializeNewAccount(String username);

    String updateEnemiesKilledByMobDeltaAndUsername(String username, enemiesKilled enemiesKilled);
}