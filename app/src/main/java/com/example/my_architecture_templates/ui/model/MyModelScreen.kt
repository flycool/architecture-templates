package com.example.my_architecture_templates.ui.model

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun MyModelScreen(
    modifier: Modifier = Modifier,
    viewModel: MyModelViewModel = hiltViewModel(),
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val items by produceState<MyModelUiState>(
        initialValue = MyModelUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }
    if (items is MyModelUiState.Success) {
        MyModelScreen(
            items = (items as MyModelUiState.Success).data,
            onSave = viewModel::addMyModel,
            modifier = Modifier
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyModelScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        var name by remember { mutableStateOf("compose") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(value = name, onValueChange = { name = it })

            Button(
                modifier = Modifier.width(96.dp),
                onClick = { onSave(name) })
            {
                Text(text = "save")
            }
        }
        items.forEach {
            Text(text = "Saved item: $it")
        }
    }
}


@Preview
@Composable
fun MyModelViewModelPreview() {
    MyModelScreen(listOf("dsf", "df", "df"), onSave = { })
}