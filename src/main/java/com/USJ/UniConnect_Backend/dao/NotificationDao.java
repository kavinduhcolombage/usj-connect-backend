package com.USJ.UniConnect_Backend.dao;

import com.USJ.UniConnect_Backend.dto.NotificationStatus;
import com.USJ.UniConnect_Backend.entities.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationDao extends MongoRepository<NotificationEntity, Long> {

    public List<NotificationEntity> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
