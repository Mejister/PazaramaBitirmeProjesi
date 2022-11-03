package com.example.pazarama_bitirme_app.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pazarama_bitirme_app.data.local.DataStoreManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
     private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashViewEvent>(replay = 0)
    val uiEvent: SharedFlow<SplashViewEvent> = _uiEvent

    init {
        checkOnBoardingVisibleStatus()
    }

    private fun checkOnBoardingVisibleStatus() {
        viewModelScope.launch {
            val isOnBoardingVisible = dataStoreManager.getOnBoardingVisible.first()
            if (checkCurrentUser()) {
                _uiEvent.emit(SplashViewEvent.NavigateToMain(true))
            } else {
                if (isOnBoardingVisible) {
                    _uiEvent.emit(SplashViewEvent.NavigateToMain(false))
                } else {
                    _uiEvent.emit(SplashViewEvent.NavigateToOnBoarding)
                }
            }
        }
    }

    private fun checkCurrentUser(): Boolean {
        firebaseAuth.currentUser?.let {
            return true
        } ?: run {
            return false
        }
    }
}

sealed class SplashViewEvent {
    object NavigateToOnBoarding : SplashViewEvent()
    class NavigateToMain(val isNavigateHome: Boolean) : SplashViewEvent()
}