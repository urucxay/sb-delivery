package ru.skillbranch.sbdelivery.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.db.dao.DishDao
import ru.skillbranch.sbdelivery.network.DeliveryService
import ru.skillbranch.sbdelivery.pref.PrefManager
import ru.skillbranch.sbdelivery.util.format
import timber.log.Timber
import java.util.*
import kotlin.coroutines.coroutineContext

class RootRepository(
    private val service: DeliveryService,
    private val dishDao: DishDao,
    private val prefManager: PrefManager
) {
    //TODO remake it later, current version is for testing
    companion object {
        private const val PAGE_SIZE = 25
        private const val LAST_PAGE = 12
    }

    suspend fun synchronizeData(ifModified: String) {
        val scope = CoroutineScope(coroutineContext + Dispatchers.IO)
        //TODO remake it later, current version is for testing
        for (page in 0..LAST_PAGE) {
            scope.launch {
                try {
                    Timber.d("request to api, page -> $page")
                    val response =
                        service.fetchDishes(
                            offset = page * PAGE_SIZE,
                            limit = PAGE_SIZE,
                            header = ifModified
                        )
                    Timber.d("write to db, page -> $page, item count -> ${response.size}")
                    dishDao.upsert(response.map { it.convertToDish() })
                } catch (t: Throwable) {
                    Timber.d("Error $t on page -> $page")
                }
            }.join()
            Timber.d("LastUpdateDate - > ${getLastSyncDate()}")
        }
    }


    fun setLastSyncDate(date: String = Date().format()) {
        prefManager.setLastSyncDate(date)
    }

    fun getLastSyncDate(): String {
        return prefManager.getLastSyncDate()
    }
}

