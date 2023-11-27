package com.map.Services;

import com.map.Domain.entities.MembershipEntity;
import com.map.Domain.entities.Notification;
import org.aspectj.weaver.ast.Not;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Notification createNotification(Notification notification);

    List<Notification> findAll();

    Optional<Notification> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);

}
