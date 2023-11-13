package com.example.hiberus_fc.squad

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiberus_fc.R
import com.example.hiberus_fc.databinding.ActivitySquadsBinding
import com.example.hiberus_fc.main.MainActivity
import com.example.hiberus_fc.utilities.MyMediaPlayer
import com.google.firebase.database.*

class SquadsActivity : AppCompatActivity() {

    val reference = FirebaseDatabase.getInstance().getReferenceFromUrl(
        "https://examen-tipo-b-default-rtdb.firebaseio.com/"
    )

    private lateinit var binding: ActivitySquadsBinding
    private lateinit var adapter: MyAdapter
    private var squadList = mutableListOf<Squad>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyMediaPlayer.getMediaPlayerInstance().stopAudioFile()
        binding.userList.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapter(squadList) // Utilizar la variable de instancia en lugar de declarar una nueva variable

        binding.userList.adapter = adapter

        val returnButton = findViewById<Button>(R.id.returnbutton)
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.search.addTextChangedListener(object : TextWatcher {
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
        val filteredSquadList: ArrayList<Squad> = ArrayList()
        for (eachSquad in squadList) {
            if (eachSquad.squadName?.toLowerCase()?.contains(text.toLowerCase()) == true ||
                eachSquad.squadCountry?.toLowerCase()?.contains(text.toLowerCase()) == true
            ) {
                filteredSquadList.add(eachSquad)
            }
        }
        adapter.filterList(filteredSquadList)
    }

    override fun onResume() {
        super.onResume()
        reference.child("Squads").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                squadList.clear()
                for (dataSnapshot in snapshot.children) {
                    val squad = dataSnapshot.getValue(Squad::class.java)
                    squad?.let { squadList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error aquí si es necesario
            }
        })
    }
}