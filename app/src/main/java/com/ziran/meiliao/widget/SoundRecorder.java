package com.ziran.meiliao.widget;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class SoundRecorder {
    private static final String LOG_TAG = "SoundRecorder";
    private MediaRecorder mRecorder;
public SoundRecorder(){
    mRecorder = new MediaRecorder();
}
    public static File getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(File audioFile) {
        this.audioFile = audioFile;
    }

    public static File audioFile;

    public void startRecording() {
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mRecorder.setMaxDuration(1000 * 60 * 10);
        try {
            audioFile = File.createTempFile("record_", ".amr");
            mRecorder.setOutputFile(audioFile.getAbsolutePath());
            mRecorder.prepare();

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed" + e.getMessage());
        }
        mRecorder.start();

    }
    public int getMaxAmplitude() {
        return mRecorder.getMaxAmplitude();
    }



    public void stopRecording() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

}