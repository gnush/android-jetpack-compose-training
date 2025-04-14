package com.example.dropdownlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropdownlist.ui.theme.DropDownListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DropDownListTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DropDownMenuInTextField()
                        DropDownMenuInOutlinedTextField()
                        DropDownMenuInTextFieldWithTextFieldValue()

                        ExposedDropDownMenuInTextField()
                        ExposedDropDownMenuInOutlinedTextField()
                        ExposedDropDownMenuInTextFieldWithTextFieldValue()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuInOutlinedTextField() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    val items = listOf("First", "Second", "Third")

    OutlinedTextField(
        label = { Text("Normal Drop Down") },
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            text = it
                            expanded = false
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuInTextField() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    val items = listOf("First", "Second", "Third")

    TextField(
        label = { Text("Normal Drop Down (TextFieldValue)") },
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        trailingIcon = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            text = it
                            expanded = false
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuInTextFieldWithTextFieldValue() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val items = listOf("First", "Second", "Third")

    TextField(
        label = { Text("Normal Drop Down") },
        value = TextFieldValue(
            text = text,
            selection = TextRange(text.length)
        ),
        onValueChange = { text = it.text },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        trailingIcon = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            text = it
                            expanded = false
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropDownMenuInOutlinedTextField() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val items = listOf("First", "Second", "Third")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            label = { Text("Exposed Drop Down") },
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            readOnly = false,
            singleLine = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        text = it
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropDownMenuInTextField() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val items = listOf("First", "Second", "Third")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            label = { Text("Exposed Drop Down") },
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            readOnly = false,
            singleLine = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        text = it
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropDownMenuInTextFieldWithTextFieldValue() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val items = listOf("First", "Second", "Third")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            label = { Text("Exposed Drop Down (TextFieldValue)") },
            value = TextFieldValue(
                text = text,
                selection = TextRange(text.length)
            ),
            onValueChange = { text = it.text },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .menuAnchor(MenuAnchorType.PrimaryEditable),
            readOnly = false,
            singleLine = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        text = it
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}