package com.kpi.epam.convertio.mongodb;


import com.kpi.epam.convertio.mongodb.domain.AudioFile;
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
    public String getAudioFormat(AudioData audio) {
        AudioFile audioFile = insertAudio(audio);
        return audioFile.way;
    }

    @Override
    public AudioFile insertAudio(AudioData audio) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("hash: ", audio.hashCode());
        DBCursor curr = hashColl.find(dbObject);

        if(curr == null)
        {
            // save file on storage
            String way = null; // get way to file
            dbObject.put("way: ", way);
            hashColl.insert(dbObject);
        } else
        {
            dbObject = hashColl.findOne(curr);
        }

        return toFileObject(dbObject);
    }

    private AudioFile toFileObject(DBObject obj)
    {
        return new AudioFile(obj.get("hash").toString(),
               obj.get("way").toString());
    }
}
