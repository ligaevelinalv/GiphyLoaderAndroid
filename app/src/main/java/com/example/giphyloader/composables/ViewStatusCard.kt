package com.example.giphyloader.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.giphyloader.R

@Composable
fun ViewStatusCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 24.dp),
            painter = painterResource(id = drawable),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(id = R.color.status_card_purple)),
        )
        if (text != null) {
            Text(
                text = stringResource(id = text),
                color = colorResource(id = R.color.status_card_purple),
                fontSize = 18.sp,
            )
        }
    }
}
