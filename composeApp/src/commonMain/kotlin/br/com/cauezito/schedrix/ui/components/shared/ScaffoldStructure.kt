package br.com.cauezito.schedrix.ui.components.shared

import BackgroundedBox
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ScaffoldStructure(
    toolbarContentDescription: String,
    onBackPressed: (() -> Unit),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            CustomAppBar(
                contentDescription = toolbarContentDescription,
                onBackPressed = onBackPressed
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        BackgroundedBox {
            content(innerPadding)
        }
    }
}