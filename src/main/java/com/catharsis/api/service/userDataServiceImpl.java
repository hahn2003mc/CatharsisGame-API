package com.catharsis.api.service;

import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import com.catharsis.api.model.*;

@Service
public class userDataServiceImpl implements userDataService {

    private final MongoTemplate mongoTemplate;

    public userDataServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public userData getUserData(String username) {
        Query query = new Query();

        query.addCriteria(
            Criteria.where("username").is(username)
        );

        return mongoTemplate.findOne(
            query,
            userData.class
        );
    }

    @Override
    public String saveUserData(userData userData) {
        String username = userData.getUsername();
        boolean accountExists = checkIfAccountExists(username);
        if (!accountExists) {
            return "ERROR: Cannot update data for user. Account with username " + username + " does not exist";
        }
        mongoTemplate.save(userData);
        return "Updated user data for " + username;
    }

    @Override
    public String initializeNewAccount(String username) {
        boolean accountExists = checkIfAccountExists(username);
        if (accountExists) {
            return "An account with username " + username + " already exists";
        }
        userData newUserData = new userData();
        statistics newStatistics = new statistics();
        enemiesKilled newEnemiesKilled = new enemiesKilled();
        newStatistics.setEnemiesKilled(newEnemiesKilled);
        newStatistics.setLevel(1);
        newUserData.setStatistics(newStatistics);
        newUserData.setUsername(username.trim());
        mongoTemplate.save(newUserData);
        return "Successfully created new account for " + username;
    }

    @Override
    public String updateEnemiesKilledByMobDeltaAndUsername(String username, enemiesKilled enemiesKilledUpdates) {
        boolean accountExists = checkIfAccountExists(username);
        if (!accountExists) {
            return "ERROR: Cannot update data. An account with username " + username + " does not exist.";
        }
        userData userData = getUserData(username);
        enemiesKilled enemiesKilled = userData.getStatistics().getEnemiesKilled();
        enemiesKilled.setSpiders(enemiesKilled.getSpiders() + enemiesKilledUpdates.getSpiders());
        enemiesKilled.setGrunts(enemiesKilled.getGrunts() + enemiesKilledUpdates.getGrunts());
        enemiesKilled.setSkeletons(enemiesKilled.getSkeletons() + enemiesKilledUpdates.getSkeletons());
        enemiesKilled.setDragons(enemiesKilled.getDragons() + enemiesKilledUpdates.getDragons());
        mongoTemplate.save(userData);
        return "Updated statistic values for user" + username;
    }

    @Override
    public String updateLevel(String username) {
        boolean accountExists = checkIfAccountExists(username);
        if (!accountExists) {
            return "ERROR: Cannot update data for user. Account with username " + username + " does not exist";
        }
        userData userData = getUserData(username);
        int currentLevel = userData.getStatistics().getLevel();
        userData.getStatistics().setLevel(currentLevel + 1);
        mongoTemplate.save(userData);
        return "Updated user data for " + username;
    }

    private boolean checkIfAccountExists(String username) {
        Query query = new Query();

        query.addCriteria(
            Criteria.where("username").is(username)
        );

        userData result = mongoTemplate.findById(username, userData.class);
        if (result != null) {
            return true;
        }
        return false;
    }

}