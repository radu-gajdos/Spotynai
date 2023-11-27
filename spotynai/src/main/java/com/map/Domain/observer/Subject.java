package com.map.Domain.observer;

import com.map.Domain.entities.UserEntity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public interface Subject {

    void addObserver(UserEntity userEntity);
    void removeObserver(UserEntity userEntity);
    void notifyObservers();

}