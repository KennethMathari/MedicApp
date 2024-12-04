package com.mobile.medicapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.mobile.medicapp.R
import com.mobile.medicapp.ui.theme.MedicAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            onClick = {},
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

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MedicAppTheme {
        LoginScreen(
            snackbarHostState = SnackbarHostState()
        )
    }

}