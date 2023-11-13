package com.example.hiberus_fc.utilities

import android.content.Context
import android.media.MediaPlayer

class MyMediaPlayer private constructor() {
    private var mediaPlayer: MediaPlayer? = null

    companion object {
        private var instance: MyMediaPlayer? = null

        fun getMediaPlayerInstance(): MyMediaPlayer {
            if (instance == null) {
                instance = MyMediaPlayer()
            }
            return instance!!
        }
    }

    fun playAudioFile(context: Context, sampleAudio: Int) {
        mediaPlayer = MediaPlayer.create(context, sampleAudio)
        mediaPlayer?.setOnPreparedListener {
            it.start()
        }
    }

    fun stopAudioFile() {
        mediaPlayer?.stop()
    }
}