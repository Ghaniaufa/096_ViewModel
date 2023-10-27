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
    var Usr: String by mutableStateOf("")
        private set
    var noTLp: String by mutableStateOf("")
        private set
    var Eml: String by mutableStateOf("")
        private set
    var jenisKL: String by mutableStateOf("")
        private set
    var Stat: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun insertData(usr:String, tlp: String, eml: String,jk: String, sts: String) {
        Usr = usr;
        noTLp = tlp;
        Eml = eml;
        jenisKL =jk;
        Stat = sts;


    }

    fun setJenisk(pilihJK: String) {
        _uiState.update { currenState -> currenState.copy(sex = pilihJK) }
    }

    fun SetStatus(pilihStatus: String) {
        _uiState.update { currentState -> currentState.copy(Status = pilihStatus) }
    }
}