package com.catharsis.api.model;

public class statistics {

    private int level;

    private enemiesKilled enemiesKilled;
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public enemiesKilled getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(enemiesKilled enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }
}
