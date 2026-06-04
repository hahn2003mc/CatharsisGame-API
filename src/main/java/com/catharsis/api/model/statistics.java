package com.catharsis.api.model;

public class statistics {

    private String level;

    private enemiesKilled enemiesKilled;
    
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public enemiesKilled getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(enemiesKilled enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }
}
