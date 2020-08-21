package ru.skillbranch.sbdelivery.ui.fragment.dish

import android.text.Spanned
import android.text.style.StrikethroughSpan
import androidx.core.text.toSpannable
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_dish.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.adapter.ReviewAdapter
import ru.skillbranch.sbdelivery.ui.base.BaseFragment
import ru.skillbranch.sbdelivery.ui.base.Binding
import ru.skillbranch.sbdelivery.ui.base.IViewModelState
import ru.skillbranch.sbdelivery.ui.base.ToolbarBuilder
import ru.skillbranch.sbdelivery.ui.custom.OffsetItemDecorator
import ru.skillbranch.sbdelivery.ui.custom.RenderProp
import ru.skillbranch.sbdelivery.ui.fragment.dialogs.ReviewDialogFragment
import ru.skillbranch.sbdelivery.util.setThrottledClickListener
import ru.skillbranch.sbdelivery.util.show

class DishFragment : BaseFragment<DishViewModel>() {

    override val layout = R.layout.fragment_dish
    override val viewModel: DishViewModel by stateViewModel { parametersOf(args.id) }
    override val binding: Binding = DishBinding()

    private val adapter = ReviewAdapter()
    private val args: DishFragmentArgs by navArgs()

    override val prepareToolbar: (ToolbarBuilder.() -> Unit) = {
        this.setTitle(args.name)
    }

    override fun setupViews() {
        //lock drawer layout menu
        root.lockDrawerLayout(true)

        tvName.text = args.name
        tvDescription.text = args.description

        btnReviewAdd.setThrottledClickListener {
            ReviewDialogFragment().show(childFragmentManager, "review_dialog")
        }

        btnFavorite.setThrottledClickListener {
            viewModel.handleToggleFavorite()
        }

        btnAddToCart.setThrottledClickListener {
            viewModel.handleAddToCart(tvNumPicker.count)
        }

        rvReviews.adapter = adapter
        rvReviews.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvReviews.addItemDecoration(OffsetItemDecorator(top = 10))

        viewModel.reviewsLiveData.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    inner class DishBinding : Binding() {

        private var oldPrice: String by RenderProp("") {
            val string = resources.getString(R.string.price, it)
            val strikethroughPrice = string.toSpannable().apply {
                setSpan(
                    StrikethroughSpan(),
                    0,
                    string.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            if (it.isNotEmpty()) {
                tvOldPrice.show()
                groupPromo.show()
                tvOldPrice.text = strikethroughPrice
            }
        }
        private var price: Int by RenderProp(0) {
            tvPrice.text = resources.getString(R.string.price, it.toString())
        }
        private var rating: Float by RenderProp(0F) { tvRating.text = it.toString() }
        private var isFavorite: Boolean by RenderProp(false) { btnFavorite.isChecked = it }
        private var image: String by RenderProp("") {
            Glide.with(requireContext())
                .load(it)
                .into(ivDish)
        }

        override fun bind(data: IViewModelState) {
            data as DishState
            isFavorite = data.isFavorite
            price = data.price
            rating = data.rating
            oldPrice = data.oldPrice ?: ""
            image = data.image ?: ""
        }

    }

}