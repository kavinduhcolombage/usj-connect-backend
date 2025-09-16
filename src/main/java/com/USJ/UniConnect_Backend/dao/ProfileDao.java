package com.USJ.UniConnect_Backend.dao;

import com.USJ.UniConnect_Backend.entities.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileDao extends MongoRepository<ProfileEntity, Long> {



}
