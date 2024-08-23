package com.example.appretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class jsonAdapter(val viewdata:List<Data>) : RecyclerView.Adapter<jsonAdapter.myviewholder>() {


    class myviewholder(val row:View) : RecyclerView.ViewHolder(row) {
        var userid:TextView = row.findViewById(R.id.userid)
        var id:TextView = row.findViewById(R.id.id)
        var title:TextView = row.findViewById(R.id.title)
        var body:TextView = row.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.jsonview,parent,false)
        return myviewholder(layout)
    }

    override fun getItemCount(): Int {
        return viewdata.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        holder.userid.text = viewdata.get(position).userId.toString()
        holder.id.text = viewdata.get(position).id.toString()
        holder.title.text = viewdata.get(position).title.toString()
        holder.body.text = viewdata.get(position).body.toString()
    }
}