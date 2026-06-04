package com.catharsis.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userData")
public class userData {
    
    @Id    
    private String username; 

    private statistics statistics;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(statistics statistics) {
        this.statistics = statistics;
    }
}
