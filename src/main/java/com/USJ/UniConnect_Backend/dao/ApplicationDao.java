package com.USJ.UniConnect_Backend.dao;

import com.USJ.UniConnect_Backend.entities.ApplicantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationDao extends MongoRepository<ApplicantEntity,Long> {
}
