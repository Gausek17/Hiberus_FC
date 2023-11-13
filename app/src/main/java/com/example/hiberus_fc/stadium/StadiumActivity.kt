package com.example.hiberus_fc.stadium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiberus_fc.R
import com.example.hiberus_fc.databinding.ActivitySquadsBinding
import com.example.hiberus_fc.databinding.ActivityStadiumBinding
import com.example.hiberus_fc.main.MainActivity
import com.example.hiberus_fc.squad.MyAdapter
import com.example.hiberus_fc.squad.Squad
import com.example.hiberus_fc.utilities.MyMediaPlayer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StadiumActivity : AppCompatActivity() {

    val reference = FirebaseDatabase.getInstance().getReferenceFromUrl(
        "https://examen-tipo-b-default-rtdb.firebaseio.com/"
    )

    private lateinit var binding: ActivityStadiumBinding
    private var stadiumList = mutableListOf<Stadium>()
    private lateinit var adapter: StadiumAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStadiumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyMediaPlayer.getMediaPlayerInstance().stopAudioFile()
        binding.stadiumList.layoutManager = LinearLayoutManager(this)

        adapter = StadiumAdapter(stadiumList) // Utilizar la variable de instancia en lugar de declarar una nueva variable

        binding.stadiumList.adapter = adapter



        val ReturnButton = findViewById<Button>(R.id.returnbutton)
        ReturnButton.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

        binding.filter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No es necesario implementar nada aquí
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No es necesario implementar nada aquí
            }
        })
    }

    fun filter(text: String) {
        val filteredStadiumList: ArrayList<Stadium> = ArrayList()
        for (eachStadium in stadiumList) {
            if (eachStadium.stadiumName?.toLowerCase()?.contains(text.toLowerCase()) == true ||
                eachStadium.stadiumCountry?.toLowerCase()?.contains(text.toLowerCase()) == true
            ) {
                filteredStadiumList.add(eachStadium)
            }
        }
        adapter.filterList(filteredStadiumList)
    }

    override fun onResume() {
        super.onResume()
        reference.child("Stadiums").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                stadiumList.clear()
                for (dataSnapshot in snapshot.children) {
                    val stadium = dataSnapshot.getValue(Stadium::class.java)
                    stadium?.let { stadiumList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}