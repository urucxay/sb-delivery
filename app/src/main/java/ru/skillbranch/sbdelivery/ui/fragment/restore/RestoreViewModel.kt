package ru.skillbranch.sbdelivery.ui.fragment.restore

import androidx.lifecycle.SavedStateHandle
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class RestoreViewModel(handle: SavedStateHandle) :
    BaseViewModel<RestoreState>(handle, RestoreState()) {
}

class RestoreState : IViewModelState