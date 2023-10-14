package com.example.giphyloader.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = colorResource(id = R.color.dark_purple)
            )
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
            cursorColor = colorResource(id = R.color.dark_purple)
        )
    )
}