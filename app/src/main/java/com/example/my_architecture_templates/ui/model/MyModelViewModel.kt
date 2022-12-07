package com.example.my_architecture_templates.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_architecture_templates.data.MyModelRepository
import com.example.my_architecture_templates.data.local.database.MyModel
import com.example.my_architecture_templates.ui.model.MyModelUiState.Success
import com.example.my_architecture_templates.ui.model.MyModelUiState.Error
import com.example.my_architecture_templates.ui.model.MyModelUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyModelViewModel @Inject constructor(
    private val myModelRepository: MyModelRepository,
) : ViewModel() {

    val uiState: StateFlow<MyModelUiState> = myModelRepository
        .myModels.map(::Success)
        .catch { Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Loading
        )

    fun addMyModel(name: String) {
        viewModelScope.launch {
            myModelRepository.add(name)
        }
    }
}

sealed interface MyModelUiState {
    object Loading : MyModelUiState
    data class Error(val throwable: Throwable) : MyModelUiState
    data class Success(val data: List<String>) : MyModelUiState
}