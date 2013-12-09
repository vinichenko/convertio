package com.kpi.epam.convertio.mongodb;


/**
 * Created by Julia on 06.12.13.
 */

public interface HashDB {

    long getNumberOfData();

    String isContain(Integer hash);

    String isContain(String hash);

    void saveData(Integer hash, String way);

    void saveData(String hash, String way);


}
