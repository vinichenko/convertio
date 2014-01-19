package com.kpi.epam.convertio.mongodb.domain;


/**
 * Created by Julia on 06.12.13.
 */

public class DBFile {
    public final String hash;
    public final String way;

    public DBFile(String hash, String way) {
        this.way = way;
        this.hash = hash;
    }

    public DBFile(Integer hash, String way) {
        this.way = way;
        this.hash = hash.toString();

    }
}
