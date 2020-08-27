package ru.skillbranch.sbdelivery.ui.fragment.register

import androidx.lifecycle.SavedStateHandle
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class RegisterViewModel(handle: SavedStateHandle) :
    BaseViewModel<RegisterState>(handle, RegisterState()) {

}

class RegisterState : IViewModelState