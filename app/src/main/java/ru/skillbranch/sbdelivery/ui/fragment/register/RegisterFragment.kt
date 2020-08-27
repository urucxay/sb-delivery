package ru.skillbranch.sbdelivery.ui.fragment.register

import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.base.BaseFragment
import ru.skillbranch.sbdelivery.ui.base.Binding
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class RegisterFragment : BaseFragment<RegisterViewModel>() {
    override val viewModel: RegisterViewModel by stateViewModel()
    override val layout: Int = R.layout.fragment_register
    override val binding: RegisterBinding = RegisterBinding()

    override fun setupViews() {

    }

    class RegisterBinding : Binding() {
        override fun bind(data: IViewModelState) {
        }
    }

}