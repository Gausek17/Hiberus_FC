package com.example.hiberus_fc.squad

import android.view.LayoutInflater
import android.view.View
import java.util.Locale
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiberus_fc.R
import com.squareup.picasso.Picasso

class MyAdapter(private var squadData : List<Squad>) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(squad: Squad){
            val squadName = itemView.findViewById<TextView>(R.id.tvfirstName)
            val squadCountry = itemView.findViewById<TextView>(R.id.tvlastName)
            val age = itemView.findViewById<TextView>(R.id.tvage)
            val squadImage = itemView.findViewById<ImageView>(R.id.imagesquad)
            squadName.text = squad.squadName
            squadCountry.text = squad.squadCountry
            age.text = squad.age

            Picasso.get().load(squad.photo).into(squadImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.squad_item, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return squadData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(squadData[position])
    }

    fun filterList(filteredCourseList: ArrayList<Squad>){
        this.squadData = filteredCourseList
        notifyDataSetChanged()
    }





}






