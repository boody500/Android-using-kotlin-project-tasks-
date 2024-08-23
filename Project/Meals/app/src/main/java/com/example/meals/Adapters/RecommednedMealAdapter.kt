package com.example.meals.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meals.Adapters.ListCategoryItemsAdapter.myviewholder
import com.example.meals.Data.MealItem
import com.example.meals.R
import com.example.meals.ViewInterfaces.MealFavButtonListener
import com.example.meals.Views.MealInfo

class RecommednedMealAdapter(var meal: MealItem, var fragmentManager: FragmentManager,var my_context:Context ,var meal_Listener: MealFavButtonListener, var userID:String): RecyclerView.Adapter<RecommednedMealAdapter.myviewholder>() {
    class myviewholder(var row: View) : RecyclerView.ViewHolder(row)  {
        val thumbnail = row.findViewById<ImageView>(R.id.Item_thumbnail)
        val name = row.findViewById<TextView>(R.id.ItemName)
        val fav_button = row.findViewById<Button>(R.id.favbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.listitems,parent,false)
        return RecommednedMealAdapter.myviewholder(layout)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
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
            fm.replace(R.id.Frame_Layout, MealInfo(meal,my_context,userID)).commit()
        }
    }

}