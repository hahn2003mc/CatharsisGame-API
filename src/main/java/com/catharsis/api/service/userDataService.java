package com.catharsis.api.service;

import com.catharsis.api.model.userData;
import org.springframework.stereotype.Service;

@Service
public interface userDataService {

    userData getUserData(String userId);

    void saveUserData(userData userData);
}