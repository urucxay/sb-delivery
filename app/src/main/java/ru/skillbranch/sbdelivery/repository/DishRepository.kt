package ru.skillbranch.sbdelivery.repositoryimport kotlinx.coroutines.flow.Flowimport ru.skillbranch.sbdelivery.db.dao.DishDaoimport ru.skillbranch.sbdelivery.db.dao.ReviewDaoimport ru.skillbranch.sbdelivery.db.entitiy.Dishimport ru.skillbranch.sbdelivery.db.entitiy.DishItemimport ru.skillbranch.sbdelivery.network.DeliveryServiceimport ru.skillbranch.sbdelivery.network.response.ReviewResponseimport ru.skillbranch.sbdelivery.pref.PrefManagerclass DishRepository(    private val service: DeliveryService,    private val dishDao: DishDao,    private val reviewDao: ReviewDao,    private val prefManager: PrefManager) {    suspend fun getReviews(dishId: String): List<ReviewResponse> = service.fetchReviews(dishId)    fun getDishById(id: String): Flow<Dish?> = dishDao.getDishById(id)    fun getDishesByRating(rating: Float = 4.8F, limit: Int = 10) =        dishDao.getDishesByRating(rating, limit)    fun getDishesWithMaxLikeCount(limit: Int = 10) = dishDao.getDishesWithMaxLikeCount(limit)    suspend fun getRecommendDishes(): Flow<List<DishItem>> {        val dishesIds = service.fetchRecommendDishes()        return dishDao.getDishesByIds(dishesIds)    }    fun toggleFavorite(id: String, isFavorite: Boolean) {    }    fun addToCart(id: String, count: Int) {    }}