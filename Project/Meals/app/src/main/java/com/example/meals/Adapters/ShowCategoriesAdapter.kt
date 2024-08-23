package com.example.meals.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import com.example.meals.Data.MealCategories
import com.example.meals.R

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meals.Views.ListCategoryItems

class ShowCategoriesAdapter(var dataofCategories: MealCategories,private val fragmentManager: FragmentManager,var context: Context,var userID:String) : RecyclerView.Adapter<ShowCategoriesAdapter.myHomeAdapter>() {
    private lateinit var cat_name:String

    class myHomeAdapter(val row: View) : RecyclerView.ViewHolder(row) {

        val thumbnail = row.findViewById<ImageView>(R.id.Category_thumbnail)
        val name = row.findViewById<TextView>(R.id.CategoryName)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHomeAdapter {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.show_categories,parent,false)
        return myHomeAdapter(layout)
    }

    override fun getItemCount(): Int {
        return dataofCategories.categories.size
    }

    override fun onBindViewHolder(holder: myHomeAdapter, position: Int) {
        holder.name.text = dataofCategories.categories.get(position).strCategory.toString()
        Glide.with(holder.row.context)
            .load(dataofCategories.categories.get(position).strCategoryThumb)
            .into(holder.thumbnail)



        holder.row.setOnClickListener{
            val fm = fragmentManager.beginTransaction()
            fm.replace(R.id.Frame_Layout, ListCategoryItems(holder.name.text.toString(),context,userID))
            fm.addToBackStack(null)
            fm.commit()

        }
    }
}