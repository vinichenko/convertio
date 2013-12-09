package com.kpi.epam.convertio.mongodb;

import com.kpi.epam.convertio.mongodb.domain.AudioFile;
import sun.audio.AudioData;

/**
 * Created by Julia on 06.12.13.
 */

public interface HashDB {

    long getNumberOfData();

    String getAudioFormat(AudioData audio);

    AudioFile insertAudio(AudioData audio);


}
