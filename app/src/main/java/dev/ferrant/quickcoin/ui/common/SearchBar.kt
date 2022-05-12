package dev.ferrant.quickcoin.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import dev.ferrant.quickcoin.R
import dev.ferrant.quickcoin.ui.theme.QuickCoinTheme

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
    QuickCoinTheme {
        SearchBar { }
    }
}
