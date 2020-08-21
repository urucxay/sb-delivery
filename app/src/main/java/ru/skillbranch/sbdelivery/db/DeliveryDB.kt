package ru.skillbranch.sbdelivery.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.skillbranch.sbdelivery.db.dao.DishDao
import ru.skillbranch.sbdelivery.db.dao.FavoriteInfoDao
import ru.skillbranch.sbdelivery.db.dao.ReviewDao
import ru.skillbranch.sbdelivery.db.entitiy.Cart
import ru.skillbranch.sbdelivery.db.entitiy.Dish
import ru.skillbranch.sbdelivery.db.entitiy.DishItem
import ru.skillbranch.sbdelivery.db.entitiy.FavoriteInfo

const val DB_NAME = "sb_delivery.database"

@Database(
    entities = [Dish::class, FavoriteInfo::class, Cart::class],
    version = 1,
    exportSchema = false,
    views = [DishItem::class]
)
@TypeConverters(Converters::class)
abstract class DeliveryDB : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean = false): DeliveryDB {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, DeliveryDB::class.java)
            } else {
                Room.databaseBuilder(context, DeliveryDB::class.java, DB_NAME)
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun dishDao(): DishDao
    abstract fun reviewDao(): ReviewDao
    abstract fun favoriteInfoDao(): FavoriteInfoDao

}
