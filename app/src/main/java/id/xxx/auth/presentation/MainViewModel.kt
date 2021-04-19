package id.xxx.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.xxx.auth.domain.user.usecase.IInteractor

class MainViewModel(iInteractor: IInteractor) : ViewModel() {

    val currentUser = iInteractor.currentUser().asLiveData()

    val signOut = { iInteractor.signOut().asLiveData() }
}