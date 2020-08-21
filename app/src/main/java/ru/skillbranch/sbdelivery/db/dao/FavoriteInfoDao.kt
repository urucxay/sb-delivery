package ru.skillbranch.sbdelivery.db.dao

import androidx.room.*
import ru.skillbranch.sbdelivery.db.entitiy.FavoriteInfo
import java.util.*

@Dao
interface FavoriteInfoDao {

    @Query(
        """
        UPDATE table_favorite_info 
        SET is_favorite = NOT is_favorite, updated_at = CURRENT_TIMESTAMP
        WHERE dish_id = :dishId
    """
    )
    fun toggleFavorite(dishId: String): Int

    @Transaction
    suspend fun toggleFavoriteOrInsert(dishId: String) {
        if (toggleFavorite(dishId) == 0) insert(
            FavoriteInfo(
                dishId = dishId,
                isFavorite = true,
                updatedAt = Date()
            )
        )
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteInfo: FavoriteInfo): Long
}