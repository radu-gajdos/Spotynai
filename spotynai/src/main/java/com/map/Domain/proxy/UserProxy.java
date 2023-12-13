package com.map.Domain.proxy;

import com.map.Domain.entities.UserEntity;
import com.map.Domain.observer.Observer;
import com.map.Domain.observer.Subject;

public class UserProxy implements Observer {
    private UserEntity realUser;

    public UserProxy(UserEntity realUser) {
        this.realUser = realUser;
    }

    @Override
    public void update(Subject subject) {
        if (realUser != null) {
            realUser.update(subject);
        }
    }

    public boolean isAdmin() {
        if (realUser != null) {
            return realUser.isAdmin();
        }
        return false;
    }

    public void setBadge() {
        if (isAdmin()) {
            if (realUser != null) {
                realUser.setBadge();
            }
        }
    }

    public Long getId() {
        if (realUser != null) {
            return realUser.getId();
        }
        return null;
    }


}