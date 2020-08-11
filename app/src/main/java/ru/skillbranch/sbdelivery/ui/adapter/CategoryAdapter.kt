package ru.skillbranch.sbdelivery.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.menu_item_view.*
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.network.response.CategoryResponse

class CategoryAdapter(private val listener: (CategoryResponse) -> Unit) :
    ListAdapter<CategoryResponse, CategoryAdapter.ViewHolder>(CategoryItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(category: CategoryResponse, listener: (CategoryResponse) -> Unit) {
            tvCategoryName.text = category.name
            Glide.with(ivCategoryImage.context)
                .load(category.icon)
                .into(ivCategoryImage)
            containerView.setOnClickListener {
                listener(category)
            }
        }
    }
}

object CategoryItemCallback : DiffUtil.ItemCallback<CategoryResponse>() {
    override fun areItemsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse) =
        oldItem.categoryId == newItem.categoryId

    override fun areContentsTheSame(oldItem: CategoryResponse, newItem: CategoryResponse) =
        oldItem == newItem
}