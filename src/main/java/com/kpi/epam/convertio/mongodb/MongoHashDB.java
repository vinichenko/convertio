package com.kpi.epam.convertio.mongodb;


import com.mongodb.*;
import sun.audio.AudioData;

import java.net.UnknownHostException;

/**
 * Created by Julia on 06.12.13.
 */

public class MongoHashDB implements HashDB {

    public static final DBObject ANY = new BasicDBObject();

    private final DB hashDB;
    private final DBCollection hashColl;

    public MongoHashDB() throws UnknownHostException {
        hashDB = new Mongo("localhost", 27017).getDB("hashDB");
        hashColl = hashDB.getCollection("hash_way");
    }

    @Override
    public long getNumberOfData() {
        return hashColl.count();
    }

    @Override
    public String isContain(Integer hash) {
        return isContain(hash.toString());
    }

    @Override
    public String isContain(String hash) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("hash: ", hash);
        DBCursor curr = hashColl.find(dbObject);

        if(curr != null)
        {
            dbObject = hashColl.findOne(curr);
            return dbObject.get("way").toString();
        }

        return null;
    }

    @Override
    public void saveData(Integer hash, String way) {
        saveData(hash.toString(), way);
    }

    @Override
    public void saveData(String hash, String way) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("hash: ", hash);
        dbObject.put("way: ", way);
        hashColl.insert(dbObject);
    }
}
