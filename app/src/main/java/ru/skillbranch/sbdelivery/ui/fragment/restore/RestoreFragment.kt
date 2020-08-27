package ru.skillbranch.sbdelivery.ui.fragment.restore

import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.ui.base.BaseFragment
import ru.skillbranch.sbdelivery.ui.base.Binding
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class RestoreFragment : BaseFragment<RestoreViewModel>() {
    override val viewModel: RestoreViewModel by stateViewModel()
    override val layout: Int = ru.skillbranch.sbdelivery.R.layout.fragment_restore
    override val binding: RestoreBinding = RestoreBinding()

    override fun setupViews() {

    }

    class RestoreBinding : Binding() {
        override fun bind(data: IViewModelState) {
        }
    }

}