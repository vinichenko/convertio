package ua.kpi.convertio.db;


import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by Julia on 06.12.13.
 */

public class MongoHashDB implements HashDB {

    public static final DBObject ANY = new BasicDBObject();

    private final DB hashDB;
    private final DBCollection hashColl;

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        new MongoHashDB().write("hash", "aWay");
    }
    public MongoHashDB() throws UnknownHostException {
        hashDB = new Mongo("192.168.43.113", 1992).getDB("hashDB");
        hashColl = hashDB.getCollection("hash_way");
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
        DBObject obj = hashColl.findOne(dbObject, hashObject());
        if(obj != null) return true;
        return false;
    }

    @Override
    public void shutDown()
    {
        hashDB.getMongo().close();
    }

    @Override
    public long getNumberOfData() {
        return hashColl.count();
    }

    @Override
    public String read(Integer hash) throws InterruptedException {
        return read(hash.toString());
    }

    @Override
    public String read(String hash) throws InterruptedException {
        DBObject dbObject = new BasicDBObject("hash", hash);
        DBObject obj = hashColl.findOne(dbObject, hashObject());

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
            hashColl.insert(dbObject);
        }
        Thread.sleep(300);
    }
}
