package com.example.meals.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meals.R
import androidx.fragment.app.FragmentManager
import com.example.meals.Data.MealItems
import com.example.meals.ViewInterfaces.MealFavButtonListener
import com.example.meals.Views.MainActivity
import com.example.meals.Views.MealInfo

class ListCategoryItemsAdapter(var dataofMealCategoryItems: MealItems, var my_context:Context,var fragmentManager:FragmentManager,var meal_Listener:MealFavButtonListener,var userID:String) : RecyclerView.Adapter<ListCategoryItemsAdapter.myviewholder>() {
    class myviewholder(var row:View) : RecyclerView.ViewHolder(row) {
        val thumbnail = row.findViewById<ImageView>(R.id.Item_thumbnail)
        val name = row.findViewById<TextView>(R.id.ItemName)
        val fav_button = row.findViewById<Button>(R.id.favbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return myviewholder(layout)
    }

    override fun getItemCount(): Int {
        return dataofMealCategoryItems.meals.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {

        val meal = dataofMealCategoryItems.meals.get(position)
        holder.name.text = meal.strMeal.toString()

        Glide.with(holder.row.context)
            .load(meal.strMealThumb)
            .into(holder.thumbnail)

        if (userID != "guest"){
            holder.fav_button.setOnClickListener {
                meal_Listener.onMealFavClick(meal)
            }
        }

        else{
            holder.fav_button.visibility = View.INVISIBLE
        }

        holder.row.setOnClickListener{
            val fm = fragmentManager.beginTransaction()
            fm.replace(R.id.Frame_Layout,MealInfo(meal,my_context,userID)).commit()
        }

    }
}