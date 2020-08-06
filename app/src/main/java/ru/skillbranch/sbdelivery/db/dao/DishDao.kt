package ru.skillbranch.sbdelivery.db.dao

import androidx.room.Dao
import androidx.room.Query
import ru.skillbranch.sbdelivery.db.entitiy.Dish

@Dao
interface DishDao {

    @Query("""
        SELECT * FROM table_dish
        WHERE category = :category
    """)
    suspend fun getDishesByCategory(category: String) : List<Dish>

    @Query("""
        SELECT * FROM table_dish
        WHERE id = :id
    """)
    suspend fun getDishById(id: String) : Dish

    @Query("""
        SELECT * FROM table_dish
        WHERE rating >= :rating
        ORDER BY rating DESC LIMIT :limit
    """)
    suspend fun getDishesByRating(rating: Double = 4.8, limit: Int = 10) : List<Dish>

    @Query("""
        SELECT * FROM table_dish
        ORDER BY likes DESC LIMIT :limit
    """)
    suspend fun getDishesWithMaxLikeCount(limit: Int = 10) : List<Dish>
}