package com.example.meals.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meals.Data.MealCountries
import com.example.meals.R
import com.example.meals.Views.ListCountryItems

class ShowCountriesAdapter(var dataofCountries:MealCountries,private val fragmentManager: FragmentManager,var context: Context,var userID:String) : RecyclerView.Adapter<ShowCountriesAdapter.myviewholder>()
{
    class myviewholder(row:View) : RecyclerView.ViewHolder(row) {
        var btn:Button = row.findViewById<Button>(R.id.Countrybtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.show_countries,parent,false)
        return myviewholder(layout)
    }

    override fun getItemCount(): Int {
        return dataofCountries.meals.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        holder.btn.text = dataofCountries.meals.get(position).strArea.toString()
        holder.btn.setOnClickListener {
            val fm = fragmentManager.beginTransaction()
            fm.replace(R.id.Frame_Layout, ListCountryItems(holder.btn.text.toString(),context,userID))
            fm.addToBackStack(null)
            fm.commit()
        }
    }

}