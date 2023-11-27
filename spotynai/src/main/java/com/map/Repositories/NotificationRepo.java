package com.map.Repositories;

import com.map.Domain.entities.MembershipEntity;
import com.map.Domain.entities.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepo extends CrudRepository<Notification, Long> {
}
