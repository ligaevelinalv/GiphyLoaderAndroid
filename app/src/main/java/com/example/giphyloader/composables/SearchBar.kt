package com.example.giphyloader.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.giphyloader.R
import com.example.giphyloader.common.SearchFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    inputText: String,
    searchFieldState: SearchFieldState,
    onSearchInputChanged: (String) -> Unit,
    onClearInputClicked: () -> Unit,
) {
    TextField(
        value = inputText,
        onValueChange = { newInput -> onSearchInputChanged(newInput) },
        leadingIcon = {
            if (searchFieldState == SearchFieldState.IDLE || searchFieldState == SearchFieldState.EMPTY) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = colorResource(id = R.color.dark_purple),
                )
            }
        },
        trailingIcon = if (searchFieldState == SearchFieldState.INPUT) {
            {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = colorResource(id = R.color.dark_purple),
                    modifier = Modifier.clickable { onClearInputClicked.invoke() },
                )
            }
        } else {
            null
        },
        placeholder = {
            Text(stringResource(R.string.search_gifs))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            containerColor = colorResource(id = R.color.light_purple),
            placeholderColor = colorResource(id = R.color.dark_purple),
            cursorColor = colorResource(id = R.color.dark_purple),
        ),
    )
}
