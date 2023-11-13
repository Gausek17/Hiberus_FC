package com.example.hiberus_fc.stadium

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.hiberus_fc.R
import com.example.hiberus_fc.squad.Squad
import com.squareup.picasso.Picasso

class StadiumAdapter(private var stadiumData : List<Stadium>) : RecyclerView.Adapter<StadiumAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stadium: Stadium){
            val stadiumName = itemView.findViewById<TextView>(R.id.stadiumName)
            val stadiumCountry = itemView.findViewById<TextView>(R.id.stadiumCountry)
            val capacity = itemView.findViewById<TextView>(R.id.tvcapacity)
            val stadiumImage = itemView.findViewById<ImageView>(R.id.stadiumImage)
            val zone= itemView.findViewById<TextView>(R.id.zone)
            stadiumName.text = stadium.stadiumName
            stadiumCountry.text = stadium.stadiumCountry
            capacity.text = stadium.capacity
            zone.text = stadium.zone

            Picasso.get().load(stadium.photo).into(stadiumImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.stadium_item, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stadiumData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stadiumData[position])
    }

    fun filterList(filteredCourseList: ArrayList<Stadium>){
        this.stadiumData = filteredCourseList
        notifyDataSetChanged()
    }




}






