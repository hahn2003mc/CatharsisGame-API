package com.catharsis.api.dto;

import com.catharsis.api.model.*;

public class updateEnemiesRequest {
    
    private String username;
    private enemiesKilled enemiesKilled;

    public String getUsername() {
        return username;
    }

    public enemiesKilled getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnemiesKilled(enemiesKilled enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }
}