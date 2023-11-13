package com.example.hiberus_fc.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import com.example.hiberus_fc.minigame.MiniGame
import com.example.hiberus_fc.R
import com.example.hiberus_fc.squad.SquadsActivity
import com.example.hiberus_fc.stadium.StadiumActivity
import com.example.hiberus_fc.utilities.MyMediaPlayer

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyMediaPlayer.getMediaPlayerInstance().stopAudioFile()
        MyMediaPlayer.getMediaPlayerInstance().playAudioFile(this, R.raw.music_menu)
        val video = findViewById<VideoView>(R.id.videoView)
        val uri:Uri = Uri.parse(
             "android.resource://" +packageName + "/raw/video"
        )

        video.setVideoURI(uri)
        video.requestFocus()
        video.resume()
        video.start()

        val MiniGameButton = findViewById<Button>(R.id.buttonMinigame)
        MiniGameButton.setOnClickListener {
            val Intent = Intent(this, MiniGame::class.java)
            startActivity(Intent)
        }

        val SquadButton = findViewById<Button>(R.id.buttonSquads)
        SquadButton.setOnClickListener {
            val Intent = Intent(this, SquadsActivity::class.java)
            startActivity(Intent)
        }

        val StadiumButton = findViewById<Button>(R.id.stadiumButton)
        StadiumButton.setOnClickListener {
            val Intent = Intent(this, StadiumActivity::class.java)
            startActivity(Intent)
        }
    }
}