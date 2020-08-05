package ru.skillbranch.sbdelivery.ui.activity.root

import androidx.lifecycle.SavedStateHandle
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class RootViewModel(handle: SavedStateHandle) :
    BaseViewModel<RootState>(handle, RootState()) {

}

class RootState : IViewModelState