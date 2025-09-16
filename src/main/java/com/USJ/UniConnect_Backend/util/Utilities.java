package com.USJ.UniConnect_Backend.util;

import com.USJ.UniConnect_Backend.entities.Sequence;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


@Component
public class Utilities {

    private static MongoOperations mongoOperations;

    @Autowired
    public Utilities(MongoOperations mongoOperations) {
        Utilities.mongoOperations = mongoOperations;
    }

    public static Long getNextSequence(String key) throws JobPortalException {
        Query query = new Query(Criteria.where("_id").is(key));
        System.out.println("printing quesryiiii ::::: " + query);

        Update update = new Update();
        update.inc("seq",1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        Sequence seq = mongoOperations.findAndModify(query, update, options, Sequence.class);
        if (seq == null) {
            throw new JobPortalException("Unable to get sequence id for key: " + key);
        }

        return seq.getSeq();
    }
}
