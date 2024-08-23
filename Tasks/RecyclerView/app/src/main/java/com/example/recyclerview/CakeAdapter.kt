package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CakeAdapter(val data: List<cake>,val context:Context) : RecyclerView.Adapter<CakeAdapter.myviewholder> (){
    class myviewholder(val row:View) : RecyclerView.ViewHolder(row) {
        var thumbnail = row.findViewById<ImageView>(R.id.imageView)
        var name = row.findViewById<TextView>(R.id.Name)
        var desc = row.findViewById<TextView>(R.id.Description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.cakeview,parent,false)
        return myviewholder(layout)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        holder.name.text = data.get(position).name
        holder.desc.text = data.get(position).desc
        holder.thumbnail.setImageResource(data.get(position).img)

        holder.row.setOnClickListener{Toast.makeText(context,holder.name.text.toString(),Toast.LENGTH_SHORT).show()}
    }
}