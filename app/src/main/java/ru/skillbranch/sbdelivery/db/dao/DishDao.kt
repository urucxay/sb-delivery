package ru.skillbranch.sbdelivery.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.skillbranch.sbdelivery.db.entitiy.Dish
import ru.skillbranch.sbdelivery.db.entitiy.DishItem

@Dao
interface DishDao {

    @Transaction
    suspend fun upsert(list: List<Dish>) {
        insert(list)
            .mapIndexed { index: Int, recordResult: Long ->
                if (recordResult == -1L) list[index] else null
            }
            .filterNotNull()
            .also { if (it.isNotEmpty()) update(it) }
    }

    @Query(
        """
        SELECT * FROM table_dish
        WHERE category = :category
    """
    )
    suspend fun getDishesByCategory(category: String): List<Dish>

    @Query(
        """
        SELECT * FROM table_dish
        WHERE id = :id
    """
    )
    fun getDishById(id: String): Flow<Dish?>

    @Query(
        """
            SELECT * FROM DishItem
            WHERE id IN (:list)
        """
    )
    fun getDishesByIds(list: List<String>): Flow<List<DishItem>>

    @Query(
        """
        SELECT item.id, item.name, item.price, item.image, item.is_favorite, item.is_promo
        FROM DishItem AS item
        LEFT JOIN table_dish AS dish ON dish.id = item.id
        WHERE dish.rating >= :rating
        ORDER BY dish.rating DESC LIMIT :limit
        """
    )
    fun getDishesByRating(rating: Float = 4.8F, limit: Int = 10): Flow<List<DishItem>>

    @Query(
        """
        SELECT item.id, item.name, item.price, item.image, item.is_favorite, item.is_promo
        FROM DishItem AS item
        LEFT JOIN table_dish AS dish ON dish.id = item.id
        ORDER BY dish.likes DESC LIMIT :limit
    """
    )
    fun getDishesWithMaxLikeCount(limit: Int = 10): Flow<List<DishItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dish: Dish): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<Dish>): List<Long>

    @Update
    suspend fun update(dish: Dish)

    @Update
    suspend fun update(list: List<Dish>)

    @Delete
    suspend fun delete(dish: Dish)

}