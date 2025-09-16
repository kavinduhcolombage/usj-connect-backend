package com.USJ.UniConnect_Backend.dao;

import com.USJ.UniConnect_Backend.entities.JobEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobDao extends MongoRepository<JobEntity,Long> {

    public List<JobEntity> findByPostedBy(long postedBy);
}
