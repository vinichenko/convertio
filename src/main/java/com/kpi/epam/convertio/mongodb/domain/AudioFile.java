package com.kpi.epam.convertio.mongodb.domain;

import sun.audio.AudioData;

/**
 * Created by Julia on 06.12.13.
 */

public class AudioFile {
    public final String hash;
    public final String way;


    public AudioFile(AudioData audio, String way) {
        this.way = way;
        hash = String.valueOf(audio.hashCode());

    }

    public AudioFile(String hash, String way) {
        this.way = way;
        this.hash = hash;
    }

    public AudioFile(Integer hash, String way) {
        this.way = way;
        this.hash = hash.toString();

    }

    @Override
    public String toString() {
        return "{\n" +
                "\thash : \"" + hash + "\", \n" +
                "\tway: \"" + way + "\", \n" +
                "}";
    }
}
