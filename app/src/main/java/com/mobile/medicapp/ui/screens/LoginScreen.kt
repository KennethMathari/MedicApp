package com.mobile.medicapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobile.medicapp.R
import com.mobile.medicapp.ui.component.Loading
import com.mobile.medicapp.ui.theme.MedicAppTheme
import com.mobile.medicapp.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by loginViewModel.loginState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    Box {
        if (loginState.isLoading) {
            Loading()
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Login")

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = modifier.padding(vertical = 10.dp),
                    label = { Text("Email Address") },
                    placeholder = { Text("Enter Email Address") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.email_icon),
                            contentDescription = "Email Icon"
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    placeholder = { Text("Enter Password") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.password_icon),
                            contentDescription = "Password Icon"
                        )
                    },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    )
                )

                Button(
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) {
                            loginViewModel.userLogin(email, password)
                        }
                    },
                    modifier = modifier
                        .padding(horizontal = 60.dp, vertical = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Login")
                }

                Row {
                    Text(text = "Don't have an account?")

                    Text(
                        text = "Create Account", fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }

    LaunchedEffect(loginState.errorMessage) {
        scope.launch {
            loginState.errorMessage?.let { errorMessage ->
                snackbarHostState.showSnackbar(errorMessage)
            }
        }

    }

    LaunchedEffect(loginState.success) {
        scope.launch {
            if (loginState.success) {
                snackbarHostState.showSnackbar("Success")
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MedicAppTheme {
        LoginScreen(
            snackbarHostState = SnackbarHostState()
        )
    }

}