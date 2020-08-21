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

//    @Query(
//        """
//        SELECT * FROM table_dish
//        WHERE category = :category
//        """
//    )
//    suspend fun getDishesByCategory(category: String): List<Dish>

    @Query(
        """
        SELECT * FROM DishItem
        WHERE id = :id
    """
    )
    fun getDishById(id: String): Flow<DishItem>

    @Query(
        """
            SELECT * FROM DishItem
            WHERE id IN (:ids)
        """
    )
    fun getDishesByIds(ids: List<String>): Flow<List<DishItem>>

    @Query(
        """
        SELECT *
        FROM DishItem
        WHERE rating >= :rating
        ORDER BY rating DESC LIMIT :limit
        """
    )
    fun getDishesByRating(rating: Float, limit: Int): Flow<List<DishItem>>

    @Query(
        """
        SELECT *
        FROM DishItem
        ORDER BY likes DESC LIMIT :limit
    """
    )
    fun getDishesWithMaxLikeCount(limit: Int): Flow<List<DishItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dish: Dish): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(list: List<Dish>): List<Long>

    @Update
    fun update(dish: Dish)

    @Update
    fun update(list: List<Dish>)

    @Delete
    fun delete(dish: Dish)

}