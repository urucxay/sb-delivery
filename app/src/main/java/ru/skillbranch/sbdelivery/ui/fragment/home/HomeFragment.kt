package ru.skillbranch.sbdelivery.ui.fragment.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.base.BaseFragment
import ru.skillbranch.sbdelivery.ui.base.MenuItemHolder
import ru.skillbranch.sbdelivery.ui.base.NavigationCommand
import ru.skillbranch.sbdelivery.ui.base.ToolbarBuilder
import ru.skillbranch.sbdelivery.ui.custom.OffsetItemDecorator
import ru.skillbranch.sbdelivery.util.setThrottledClickListener
import ru.skillbranch.sbdelivery.util.show
import timber.log.Timber

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val viewModel: HomeViewModel by stateViewModel()
    override val layout: Int = R.layout.fragment_home

    //add cart menu
    override val prepareToolbar: (ToolbarBuilder.() -> Unit) = {
        this.addMenuItem(MenuItemHolder(
            title = "Cart",
            menuId = R.id.action_cart,
            icon = R.drawable.ic_cart
        ) {
            viewModel.navigate(NavigationCommand.To(R.id.cartDestination))
        })
    }

    //setup adapters
    private val recommendAdapter = DishAdapter(
        listener = {
            val action = HomeFragmentDirections.actionHomeDestinationToDishFragment(
                id = it.id,
                name = it.name,
                price = it.price.toString(),
                oldPrise = it.oldPrice,
                description = it.description
            )
            viewModel.navigate(NavigationCommand.To(action.actionId, action.arguments))
        },
        favoriteListener = { viewModel.handleToggleFavorite(it.id) },
        addToCartListener = { viewModel.handleAddToCart(it.id) }
    )
    private val bestAdapter = DishAdapter(
        listener = {
            val action = HomeFragmentDirections.actionHomeDestinationToDishFragment(
                id = it.id,
                name = it.name,
                price = it.price.toString(),
                oldPrise = it.oldPrice,
                description = it.description
            )
            viewModel.navigate(NavigationCommand.To(action.actionId, action.arguments))
        },
        favoriteListener = { viewModel.handleToggleFavorite(it.id) },
        addToCartListener = { viewModel.handleAddToCart(it.id) }
    )
    private val popularAdapter = DishAdapter(
        listener = {
            val action = HomeFragmentDirections.actionHomeDestinationToDishFragment(
                id = it.id,
                name = it.name,
                price = it.price.toString(),
                oldPrise = it.oldPrice,
                description = it.description
            )
            viewModel.navigate(NavigationCommand.To(action.actionId, action.arguments))
        },
        favoriteListener = { viewModel.handleToggleFavorite(it.id) },
        addToCartListener = { viewModel.handleAddToCart(it.id) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setupViews() {

        //setup RecyclerViews
        rvRecommend.setupRV(recommendAdapter)
        rvBest.setupRV(bestAdapter)
        rvPopular.setupRV(popularAdapter)

        tvBestSeeAll.setThrottledClickListener { viewModel.navigate(NavigationCommand.To(R.id.menuDestination)) }
        tvPopularSeeAll.setThrottledClickListener { viewModel.navigate(NavigationCommand.To(R.id.menuDestination)) }
        tvRecommendSeeAll.setThrottledClickListener { viewModel.navigate(NavigationCommand.To(R.id.menuDestination)) }

        //setup observers
        viewModel.recommendDishes.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                groupRecommend.show()
                Timber.d("Recommend dishes -> $it")
                recommendAdapter.submitList(it)
            }
        }
        viewModel.bestDishes.observe(viewLifecycleOwner) {
            bestAdapter.submitList(it)
        }

        viewModel.popularDishes.observe(viewLifecycleOwner) {
            popularAdapter.submitList(it)
        }
    }

    private fun RecyclerView.setupRV(adapter: DishAdapter) {
        this.adapter = adapter
        this.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        this.addItemDecoration(OffsetItemDecorator(left = 8))
    }

}