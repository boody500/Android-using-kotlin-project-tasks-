package com.example.meals.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meals.Data.MealItem
import com.example.meals.R
import com.example.meals.ViewInterfaces.MealDeleteButtonListener

class FavMealsAdapter(var dataofMeals: Array<MealItem>,var mealListener:MealDeleteButtonListener): RecyclerView.Adapter<FavMealsAdapter.myviewholder>()
{
    class myviewholder(var row: View): RecyclerView.ViewHolder(row){
        val thumbnail = row.findViewById<ImageView>(R.id.Item_thumbnail)
        val name = row.findViewById<TextView>(R.id.ItemName)
        val button = row.findViewById<Button>(R.id.favbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return FavMealsAdapter.myviewholder(layout)
    }

    override fun getItemCount(): Int {
        return  dataofMeals.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val meal = dataofMeals.get(position)
        holder.name.text = meal.strMeal.toString()

        Glide.with(holder.row.context)
            .load(meal.strMealThumb)
            .into(holder.thumbnail)

        holder.button.text = "Delete"

        holder.button.setOnClickListener {
            mealListener.onMealDeleteClick(meal)
        }

    }

}