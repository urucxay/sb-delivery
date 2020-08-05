package ru.skillbranch.sbdelivery.ui.fragment.dish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dish.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.network.response.DishResponse
import ru.skillbranch.sbdelivery.ui.adapter.DishAdapter
import ru.skillbranch.sbdelivery.ui.adapter.ReviewAdapter
import ru.skillbranch.sbdelivery.ui.base.BaseFragment
import ru.skillbranch.sbdelivery.ui.custom.OffsetItemDecorator
import ru.skillbranch.sbdelivery.ui.fragment.dialogs.ReviewDialogFragment

class DishFragment : BaseFragment<DishViewModel>() {

    override val layout = R.layout.fragment_dish
    override val viewModel: DishViewModel by stateViewModel { parametersOf(args.id) }

    private val adapter = ReviewAdapter()
    private val args: DishFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setupViews() {
        btnReviewAdd.setOnClickListener {
            ReviewDialogFragment().show(childFragmentManager, "dialog")
        }

        viewModel.reviews.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        rvReviews.adapter = adapter
        rvReviews.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvReviews.addItemDecoration(OffsetItemDecorator(top = 10))
    }


}