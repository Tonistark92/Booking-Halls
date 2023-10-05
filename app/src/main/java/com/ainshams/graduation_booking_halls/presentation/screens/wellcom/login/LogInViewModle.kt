package com.ainshams.graduation_booking_halls.presentation.screens.wellcom.login

import android.content.Context
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.ainshams.graduation_booking_halls.domain.repository.HallRepository
import com.ainshams.graduation_booking_halls.presentation.screens.core.home.HomeState
import com.ainshams.graduation_booking_halls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModle @Inject constructor(
    private val repository: HallRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(LogInState())
    val snackbarHostState = SnackbarHostState()
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    var auth    = mutableStateOf(sharedPreferences.getString("key", "") ?: "")
init {
    auth.value = sharedPreferences.getString("auth","").toString()
}
    fun getAuth()  {
        viewModelScope.launch {
            repository.getAuth(email = "islam@sci.com", password = "123456")
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)

                        }

                        is Resource.Success -> {
                            if (result.data!! < 0) {
                                state.isAuth = false
                                snackbarHostState.showSnackbar(
                                    "Not Authentic ${result.data!!} ",
                                    duration = SnackbarDuration.Short
                                )
                                sharedPreferences.edit {
                                    putString("auth", "NAUTH")
                                    apply()
                                }
                            } else {
                                state.empId = result.data

                                Log.i("Tag", result.data.toString())
                                state.isAuth = true
                                snackbarHostState.showSnackbar(
                                    " Authentic ${result.data!!} ",
                                    duration = SnackbarDuration.Short
                                )

                                sharedPreferences.edit {
                                    putString("auth", "AUTH")
                                    apply()


                                }

                            }
                        }
                        is Resource.Error -> {
                            state.networkError = true

                        }

                    }

                }
            joinAll()

        }
    }

}