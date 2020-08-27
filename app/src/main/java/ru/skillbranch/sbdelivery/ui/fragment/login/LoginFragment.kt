package ru.skillbranch.sbdelivery.ui.fragment.login

import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.base.BaseFragment
import ru.skillbranch.sbdelivery.ui.base.Binding
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class LoginFragment : BaseFragment<LoginViewModel>() {

    override val layout: Int = R.layout.fragment_login
    override val viewModel: LoginViewModel by stateViewModel()
    override val binding: LoginBinding = LoginBinding()

    override fun setupViews() {

    }

    class LoginBinding : Binding() {

        override fun bind(data: IViewModelState) {

        }

    }

}