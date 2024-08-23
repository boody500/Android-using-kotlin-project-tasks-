package com.example.meals.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Query("SELECT * FROM Meals WHERE userID = :userID")
    suspend fun getMeals(userID:String):Array<MealItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeal(meal:MealItem):Long

    @Delete
    suspend fun deleteMeal(meal:MealItem):Int
}