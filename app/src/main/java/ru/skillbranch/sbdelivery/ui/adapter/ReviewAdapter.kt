package ru.skillbranch.sbdelivery.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.review_item_view.*
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.network.response.ReviewResponse
import ru.skillbranch.sbdelivery.util.formatApiResponseDate
import timber.log.Timber

class ReviewAdapter : ListAdapter<ReviewResponse, ReviewAdapter.ViewHolder>(ReviewItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(review: ReviewResponse) {
            Timber.d("date -> ${review.date}")
            tvReviewAuthor.text = review.author + ", "
            tvReviewDate.text = review.date.formatApiResponseDate()
            tvReviewText.text = review.text
            rbReviewRating.numStars = review.rating.toInt()
            rbReviewRating.rating = review.rating.toFloat()
        }
    }
}

object ReviewItemCallback : DiffUtil.ItemCallback<ReviewResponse>() {
    override fun areItemsTheSame(oldItem: ReviewResponse, newItem: ReviewResponse) =
        oldItem.dishId == newItem.dishId

    override fun areContentsTheSame(oldItem: ReviewResponse, newItem: ReviewResponse) =
        oldItem == newItem
}