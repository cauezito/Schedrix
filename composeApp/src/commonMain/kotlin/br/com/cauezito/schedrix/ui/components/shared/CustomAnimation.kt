package br.com.cauezito.schedrix.ui.components.shared

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import schedrix.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun CustomAnimation(
    animationPath: String = "files/schedrix_loading.json",
    contentDescription: String = "Loading in progress! Wait a moment."
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes(animationPath).decodeToString()
        )
    }

    Image(
        painter = rememberLottiePainter(
            composition = composition,
            iterations = Compottie.IterateForever
        ),
        contentDescription = contentDescription
    )
}