package ru.skillbranch.sbdelivery.ui.fragment.login

import androidx.lifecycle.SavedStateHandle
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class LoginViewModel(handle: SavedStateHandle) : BaseViewModel<LoginState>(handle, LoginState()) {

}

class LoginState : IViewModelState