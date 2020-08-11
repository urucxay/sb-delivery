package ru.skillbranch.sbdelivery.ui.fragment.menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.search_view_layout.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.adapter.CategoryAdapter
import ru.skillbranch.sbdelivery.ui.base.*
import ru.skillbranch.sbdelivery.ui.custom.RenderProp

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
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        }

    }

    inner class MenuBinding : Binding() {
        var isFocusedSearch: Boolean = false
        var searchQuery: String? = null
        var isSearch: Boolean = false
        var isLoading: Boolean by RenderProp(true) { }

        override fun bind(data: IViewModelState) {
            data as MenuState
//            isSearch = data.isSearch
//            searchQuery = data.searchQuery
            isLoading = data.isLoading
        }

//        override fun saveUi(outState: Bundle) {
//            outState.putBoolean(::isFocusedSearch.name, search_view?.hasFocus() ?: false)
//        }
//
//        override fun restoreUi(savedState: Bundle?) {
//            isFocusedSearch = savedState?.getBoolean(::isFocusedSearch.name) ?: false
//        }
    }
}
