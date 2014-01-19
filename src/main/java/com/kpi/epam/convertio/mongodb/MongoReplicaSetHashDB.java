package com.kpi.epam.convertio.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by Julia on 23.12.13.
 */

public class MongoReplicaSetHashDB implements HashDB {

    public static final DBObject ANY = new BasicDBObject();
    final Mongo mongo;
    final DBCollection coll;

    public MongoReplicaSetHashDB() throws UnknownHostException {
        mongo = new Mongo(new MongoURI("mongodb://127.0.0.1:27017,127.0.0.1:27018,127.0.0.1:27019"));
        mongo.setWriteConcern(WriteConcern.REPLICA_ACKNOWLEDGED);
        mongo.slaveOk();

        coll  = mongo.getDB("hashDB").getCollection("hash_way");
    }

    private DBObject hashObject()
    {
        DBObject obj = new BasicDBObject();
        obj.put("hash", "");
        obj.put("way", "");
        return obj;
    }

    private boolean isContain(DBObject dbObject)
    {
        DBObject obj = coll.findOne(dbObject, hashObject());
        if(obj != null) return true;
        return false;
    }

    @Override
    public long getNumberOfData() {
        return coll.count();
    }

    @Override
    public String read(Integer hash) throws InterruptedException {
        return read(hash.toString());
    }

    public String read(String hash) throws InterruptedException {
        DBObject dbObject = new BasicDBObject("hash", hash);
        DBObject obj = coll.findOne(dbObject, hashObject());

        Thread.sleep(300);

        if( obj != null )
            return  obj.get("way").toString();
        else
            return  null;
    }

    @Override
    public void write(Integer hash, String way) throws InterruptedException {
         write(hash.toString(), way);
    }

    @Override
    public void write(String hash, String way) throws InterruptedException {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("hash", hash);
        if(!isContain(dbObject))
        {
            dbObject.put("way", way);
            coll.insert(dbObject);
        }
        Thread.sleep(300);
    }
}
