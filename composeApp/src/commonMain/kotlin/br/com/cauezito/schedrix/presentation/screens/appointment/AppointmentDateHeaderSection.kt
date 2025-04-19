package br.com.cauezito.schedrix.presentation.screens.appointment

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.PARTNER_WELCOME_MESSAGE
import br.com.cauezito.schedrix.ui.tokens.AppointmentStrings.PARTNER_WELCOME_TOPIC_ANIMATION
import br.com.cauezito.schedrix.ui.components.shared.AnimatedHeader
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_16
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_60
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_78
import br.com.cauezito.schedrix.ui.tokens.Dimens.dimens_8
import br.com.cauezito.schedrix.ui.tokens.Numbers.ONE
import br.com.cauezito.schedrix.ui.tokens.Numbers.THREE
import br.com.cauezito.schedrix.ui.tokens.Numbers.ZERO
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import schedrix.composeapp.generated.resources.Res
import schedrix.composeapp.generated.resources.img_user_profile

private const val TOPIC_DELAY_ANIMATION_MILLIS = 1600L

@Composable
internal fun AppointmentDateHeaderSection(interviewerName: String) {
    AnimatedHeader {
        Column(modifier = Modifier.padding(horizontal = dimens_16, vertical = dimens_78)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = dimens_16)
            ) {
                Image(
                    modifier = Modifier.size(dimens_60),
                    painter = painterResource(Res.drawable.img_user_profile),
                    contentDescription = null
                )

                Column(modifier = Modifier.padding(horizontal = dimens_16)) {
                    Text(
                        interviewerName,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(dimens_8))

                    AnimatedTopicText()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedTopicText() {
    val topics = listOf(
        "programming",
        "video games",
        "job offers",
        "book recommendations",
        "wellness",
        "mental health",
        "investments",
        "travel experiences",
        "productivity hacks",
        "career growth",
        "language learning",
        "healthy habits",
        "mindfulness",
        "gym",
        "book recommendations",
        "coding challenges",
        "public speaking",
        "UX design"
    )

    var index by remember { mutableStateOf(ZERO) }
    val topic by rememberUpdatedState(topics[index])

    LaunchedEffect(Unit) {
        while (true) {
            delay(TOPIC_DELAY_ANIMATION_MILLIS)
            index = (index + ONE) % topics.size
        }
    }

    Text(
        text = PARTNER_WELCOME_MESSAGE,
        color = Color.White
    )

    AnimatedContent(
        targetState = topic,
        transitionSpec = {
            slideInVertically { it } + fadeIn() with
                    slideOutVertically { - it } + fadeOut()
        },
        label = PARTNER_WELCOME_TOPIC_ANIMATION
    ) { animatedTopic ->
        Text(
            text = animatedTopic,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            maxLines = THREE
        )
    }
}