package com.example.act_data_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.act_data_app.data.DataForm
import com.example.act_data_app.data.DataSource.jenis
import com.example.act_data_app.data.DataSource.setatus
import com.example.act_data_app.ui.theme.Act_data_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Act_data_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun TampilLayout(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ){
            TampilForm()
        }
    }
}

@Composable
fun SelectStat(options: Any) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel1: CobaViewModel1  = viewModel()) {
    var textUsername by remember { mutableStateOf("")}
    var textTlp by remember { mutableStateOf("")}
    var textEmail by remember { mutableStateOf("")}

    val context = LocalContext.current
    val dataForm: DataForm
    val uiState by cobaViewModel1.uiState.collectAsState()
    dataForm = uiState

    OutlinedTextField(
            value = textUsername,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = {Text(text = "Username") },
            onValueChange = {
                textUsername = it
            }
        )
        OutlinedTextField(
            value = textTlp,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = {Text(text = "Telepon") },
            onValueChange = {
                textTlp = it
            }
        )
    OutlinedTextField(
        value = textEmail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = {Text(text = "email") },
        onValueChange = {
            textEmail = it
        }
    )
    selectJK(
        options = jenis.map { id -> context.resources.getString(id) },
        onSelectionChanged = { cobaViewModel1.setJenisk(it) }
    )
    SelectStat(
        options = setatus.map { id -> context.resources.getString(id) },
        onSelectionChanged = { cobaViewModel1.Stat(it)}
    )
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            cobaViewModel1.insertData(textUsername, textTlp, textEmail, dataForm.status, dataForm.sex)
        }
    ){
        Text(text = stringResource(R.string.submit))
    }
    Spacer(modifier = Modifier.height(100.dp))
    TextHasil(

}

@Composable
fun selectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        options.forEach{ item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}


@Composable
fun selectStat(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Row(modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Column(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}



@Composable
fun TextHasil(JenisKelamin: String, Status: String, Alamat: String, Email: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = "Jenis Kelamin : " + JenisKelamin,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Status : " + Status,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "Alamat : " + Alamat,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "Email : " + Email,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Act_data_appTheme {
        TampilLayout()
    }
}