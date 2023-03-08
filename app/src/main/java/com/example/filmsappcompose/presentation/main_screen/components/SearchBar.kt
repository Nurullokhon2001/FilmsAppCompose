package com.example.filmsappcompose.presentation.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onValueChange: (String) -> Unit, leadingIconClicked: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val message = remember { mutableStateOf("") }
    val isSearchVisible = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
    ) {
        if (isSearchVisible.value) {
            OutlinedTextField(
                value = message.value,
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
                onValueChange = { newText ->
                    message.value = newText
                    onValueChange(newText)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedLabelColor = Color.Black,
                    cursorColor = Color.Black
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close",
                        Modifier.clickable {
                            isSearchVisible.value = false
                            message.value = ""
                            leadingIconClicked.invoke()
                        })
                },
                label = { Text(text = "Введите текст для поиска") },
            )
        } else {
            Image(
                imageVector = Icons.Default.Search,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .size(30.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        isSearchVisible.value = true
                    },
                contentDescription = null,
            )
        }
    }
}