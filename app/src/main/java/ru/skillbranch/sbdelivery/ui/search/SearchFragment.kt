package ru.skillbranch.sbdelivery.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.search_view_layout.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.base.*
import ru.skillbranch.sbdelivery.ui.custom.RenderProp
import ru.skillbranch.sbdelivery.util.setMarginOptionally
import ru.skillbranch.sbdelivery.util.setPaddingOptionally

class SearchFragment : BaseFragment<SearchViewModel>() {
    override val layout: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by stateViewModel()
    override val binding: SearchBinding = SearchBinding()

    override val prepareToolbar: (ToolbarBuilder.() -> Unit) = {
        this.addMenuItem(
            MenuItemHolder(
                title = "Search",
                menuId = R.id.action_search,
                icon = R.drawable.ic_search,
                actionViewLayout = R.layout.search_view_layout
            )
        )
    }


    override fun setupViews() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_dish)

        //delete default left margin
        val frame = searchView.findViewById<View>(R.id.search_edit_frame)
        frame.setMarginOptionally(left = 0)
        //delete default left padding and setup textSize like toolbar's title
        val textView = searchView.findViewById<SearchView.SearchAutoComplete>(R.id.search_src_text)
        textView.setPaddingOptionally(left = 0)
        textView.textSize = 20f
        //change close button color
        val iconClose = searchView.findViewById<ImageView>(R.id.search_close_btn)
        iconClose.setColorFilter(resources.getColor(R.color.color_gray, requireContext().theme))


        //always expand searchView
        searchItem.expandActionView()
        searchView.setQuery(binding.searchQuery, false)
        //restore focus to searchView if need
        if (binding.isFocusedSearch) searchView.requestFocus()
        else searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.handleSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.handleSearch(newText)
                return true
            }
        })

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                //popUp to Menu fragment when back is pressed
                findNavController().popBackStack()
                return true
            }
        })
    }

    inner class SearchBinding : Binding() {
        var isFocusedSearch: Boolean = false
        var searchQuery: String? = null
        var isLoading: Boolean by RenderProp(true) { }

        override fun bind(data: IViewModelState) {
            data as SearchState
            searchQuery = data.searchQuery
            isLoading = data.isLoading
        }

        override fun saveUi(outState: Bundle) {
            outState.putBoolean(::isFocusedSearch.name, search_view?.hasFocus() ?: false)
        }

        override fun restoreUi(savedState: Bundle?) {
            isFocusedSearch = savedState?.getBoolean(::isFocusedSearch.name) ?: false
        }
    }

}