package com.williamhill.core_ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    leadingIcon: @Composable (() -> Unit)? =
        { Icon(Icons.Rounded.Search, contentDescription = null) },
    placeholder: @Composable (() -> Unit)? =
        { Text(text = stringResource(id = R.string.search_placeholder)) },
    onValueChange: (String) -> Unit
) {
    var search by remember { mutableStateOf(TextFieldValue("")) }
    
    TextField(
        modifier = modifier,
        value = search,
        onValueChange = {
            search = it
            onValueChange(it.text)
        },
        leadingIcon = leadingIcon,
        placeholder = placeholder,
        shape = shape,
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
    )
}

@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar { }
}
