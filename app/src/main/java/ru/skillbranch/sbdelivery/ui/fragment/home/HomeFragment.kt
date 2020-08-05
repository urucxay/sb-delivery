package ru.skillbranch.sbdelivery.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.adapter.DishAdapter
import ru.skillbranch.sbdelivery.ui.base.BaseFragment
import ru.skillbranch.sbdelivery.ui.custom.OffsetItemDecorator

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val viewModel: HomeViewModel by stateViewModel()
    override val layout: Int = R.layout.fragment_home

    //    private val recommendAdapter = DishAdapter()
    private val bestAdapter = DishAdapter() {
        val action = HomeFragmentDirections.actionHomeDestinationToDishFragment(it.id)
        findNavController().navigate(action)
    }
//    private val popularAdapter = DishAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dishes.observe(viewLifecycleOwner, Observer {
            bestAdapter.submitList(it)
        })

    }

    override fun setupViews() {
//        rvRecommend.adapter = recommendAdapter
//        rvRecommend.addItemDecoration(HorizontalSpacingItemDecorator(recommendAdapter))

        rvBest.adapter = bestAdapter
        rvBest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvBest.addItemDecoration(OffsetItemDecorator(left = 8))

//        rvPopular.adapter = popularAdapter
//        rvPopular.addItemDecoration(HorizontalSpacingItemDecorator(popularAdapter))
    }


}