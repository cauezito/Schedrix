package br.com.cauezito.schedrix.ui.components.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_1
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_12
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_16
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_58
import br.com.cauezito.schedrix.ui.tokens.Numbers.TWO
import br.com.cauezito.schedrix.ui.tokens.OrangeAmber

@Composable
internal fun TimeSelectorItem(
    time: String,
    onSelectedTime: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimens_12))
            .border(
                border = BorderStroke(width = dimens_1, Color.White),
                shape = RoundedCornerShape(dimens_16)
            )
            .height(dimens_58)
            .background(color = OrangeAmber)
            .clickable { onSelectedTime(time) }
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = time,
            minLines = TWO
        )
    }
}