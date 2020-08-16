package ru.skillbranch.sbdelivery.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dish_item_view.*
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.db.entitiy.DishItem
import ru.skillbranch.sbdelivery.util.hide
import ru.skillbranch.sbdelivery.util.setThrottledClickListener
import ru.skillbranch.sbdelivery.util.show

class DishAdapter(
    private val listener: (DishItem) -> Unit,
    private val favoriteListener: (DishItem) -> Unit,
    private val addToCartListener: (DishItem) -> Unit
) : ListAdapter<DishItem, DishAdapter.ViewHolder>(DishItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dish_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], listener, favoriteListener, addToCartListener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(
            dish: DishItem,
            listener: (DishItem) -> Unit,
            favoriteListener: (DishItem) -> Unit,
            addToCartListener: (DishItem) -> Unit
        ) {
            tvName.text = dish.name
            tvPrice.text = tvPrice.context.getString(R.string.price, dish.price.toString())
            btnFavorite.isChecked = dish.isFavorite
            if (dish.oldPrice != null) {
                groupPromo.show()
            } else {
                groupPromo.hide()
            }
            Glide.with(ivDish.context)
                .load(dish.image)
                .into(ivDish)

            containerView.setThrottledClickListener {
                listener(dish)
            }
            btnFavorite.setThrottledClickListener {
                favoriteListener(dish)
            }
            btnAddToCart.setThrottledClickListener {
                addToCartListener(dish)
            }
        }
    }
}

object DishItemCallback : DiffUtil.ItemCallback<DishItem>() {
    override fun areItemsTheSame(oldItem: DishItem, newItem: DishItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DishItem, newItem: DishItem) =
        oldItem == newItem
}