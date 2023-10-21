package com.example.act_data_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.act_data_app.data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel1 : ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var noTLp: String by mutableStateOf("")
        private set
    var jenisKL: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun insertData(nm:String, tlp: String, jk: String) {
        namaUsr = nm;
        noTLp = tlp;
        jenisKL =jk;


    }

    fun setJenisk(pilihJK: String) {
        _uiState.update { currenState -> currenState.copy(sex = pilihJK) }
    }
}