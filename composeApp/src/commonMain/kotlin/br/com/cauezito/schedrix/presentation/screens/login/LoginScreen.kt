package br.com.cauezito.schedrix.presentation.screens.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

internal class LoginScreen() : Screen {
    @Composable
    override fun Content() {
        val onLogin: () -> Unit = {}
        val onForgotPassword: () -> Unit = {}
        val onCreateAccount: () -> Unit = {}

        LoginContent(
            onLogin,
            onForgotPassword,
            onCreateAccount
        )
    }

    @Composable
    private fun LoginContent(
        onLogin: () -> Unit,
        onForgotPassword: () -> Unit,
        onCreateAccount: () -> Unit
    ) {
        LoginSection(
            onLogin,
            onForgotPassword,
            onCreateAccount
        )
    }
}