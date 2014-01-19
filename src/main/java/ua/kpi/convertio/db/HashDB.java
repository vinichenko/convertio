package ua.kpi.convertio.db;


/**
 * Created by Julia on 06.12.13.
 */

public interface HashDB {

    long getNumberOfData();

    String read(Integer hash) throws InterruptedException;

    String read(String hash) throws InterruptedException;

    void write(Integer hash, String way) throws InterruptedException;

    void write(String hash, String way) throws InterruptedException;

    void shutDown();


}
