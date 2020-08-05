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
import ru.skillbranch.sbdelivery.network.response.DishResponse

class DishAdapter(private val listener: (DishResponse) -> Unit) : ListAdapter<DishResponse, DishAdapter.ViewHolder>(DishItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dish_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(dish: DishResponse, listener: (DishResponse) -> Unit) {
            tvName.text = dish.name
            tvPrice.text = tvPrice.context.getString(R.string.price, dish.price.toString())
            Glide.with(ivDish.context)
                .load(dish.image)
                .into(ivDish)
            containerView.setOnClickListener {
                listener(dish)
            }
        }
    }
}

object DishItemCallback : DiffUtil.ItemCallback<DishResponse>() {
    override fun areItemsTheSame(oldItem: DishResponse, newItem: DishResponse) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DishResponse, newItem: DishResponse) =
        oldItem == newItem
}