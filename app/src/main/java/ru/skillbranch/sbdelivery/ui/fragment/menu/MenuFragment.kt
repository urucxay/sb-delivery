package ru.skillbranch.sbdelivery.ui.fragment.menu

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.adapter.AutoFitGridLayoutManager
import ru.skillbranch.sbdelivery.ui.adapter.CategoryAdapter
import ru.skillbranch.sbdelivery.ui.base.*
import ru.skillbranch.sbdelivery.ui.custom.RenderProp
import ru.skillbranch.sbdelivery.util.dpToIntPx

class MenuFragment : BaseFragment<MenuViewModel>() {

    override val viewModel: MenuViewModel by stateViewModel()
    override val layout: Int = R.layout.fragment_menu
    override val binding: MenuBinding by lazy { MenuBinding() }
    private val categoryAdapter by lazy { CategoryAdapter {} }

    override val prepareToolbar: (ToolbarBuilder.() -> Unit) = {
        this.addMenuItem(
            MenuItemHolder(
                "search",
                R.id.action_search,
                R.drawable.ic_search,
                null
            ) {
                val action = MenuFragmentDirections.actionMenuDestinationToSearchFragment()
                findNavController().navigate(action)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setupViews() {

        with(rvMenu) {
            adapter = categoryAdapter
            layoutManager = AutoFitGridLayoutManager(requireContext(), context.dpToIntPx(100))
        }
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

    }

    class MenuBinding : Binding() {
        var isLoading: Boolean by RenderProp(true) { }

        override fun bind(data: IViewModelState) {
            data as MenuState
            isLoading = data.isLoading
        }

    }
}
