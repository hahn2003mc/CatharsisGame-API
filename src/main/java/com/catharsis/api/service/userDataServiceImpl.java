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
    public void saveUserData(userData userData) {
        mongoTemplate.save(userData);
    }

}