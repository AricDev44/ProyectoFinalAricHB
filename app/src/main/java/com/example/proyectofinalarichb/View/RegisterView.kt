package com.example.proyectofinalarichb.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalarichb.Components.SpaceV
import com.example.proyectofinalarichb.ViewModel.AuthViewModel
import com.example.proyectofinalarichb.ui.theme.ProyectoFinalAricHBTheme
import com.example.proyectofinalarichb.ui.theme.TextDM

@Composable
fun RegisterView(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit,
    authVM: AuthViewModel? = if (LocalInspectionMode.current) null else viewModel()
){
    RegisterViewContent(
        email = authVM?.email ?: "",
        password = authVM?.password ?: "",
        errorMessage = authVM?.errorMessage,
        isLoading = authVM?.isLoading ?: false,
        onEmailChange = { authVM?.email = it },
        onPassWordChange = { authVM?.password = it },
        onRegister = {
            authVM?.register(onRegisterSuccess) ?: onRegisterSuccess()
        },
        onNavigateBack = {
            authVM?.clearError()
            onNavigateBack()
        }
    )
}

@Composable
fun RegisterViewContent(
    email: String,
    password: String,
    errorMessage: String?,
    isLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPassWordChange: (String) -> Unit,
    onRegister: () -> Unit,
    onNavigateBack: () -> Unit
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Crear una cuenta",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextDM
            )

            SpaceV(32)

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Correo electrónico", color = TextDM) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = TextDM
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextDM,
                    unfocusedTextColor = TextDM,
                    focusedLabelColor = TextDM,
                    unfocusedLabelColor = TextDM,
                    focusedLeadingIconColor = TextDM,
                    unfocusedLeadingIconColor = TextDM,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            SpaceV(16)

            OutlinedTextField(
                value = password,
                onValueChange = onPassWordChange,
                label = { Text("Contraseña", color = TextDM) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = TextDM
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextDM,
                    unfocusedTextColor = TextDM,
                    focusedLabelColor = TextDM,
                    unfocusedLabelColor = TextDM,
                    focusedLeadingIconColor = TextDM,
                    unfocusedLeadingIconColor = TextDM,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            errorMessage?.let { error ->
                SpaceV(8)
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            SpaceV(24)

            Button(
                onClick = onRegister,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading
            ){
                if (isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = TextDM,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Crear una nueva cuenta", color = TextDM, fontWeight = FontWeight.Bold)
                }
            }

            SpaceV(16)

            TextButton(onClick = onNavigateBack) {
                Text("¿Ya tienes una cuenta? Inicia sesión", color = TextDM)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    ProyectoFinalAricHBTheme(dynamicColor = false) {
        RegisterViewContent(
            email = "",
            password = "",
            errorMessage = null,
            isLoading = false,
            onEmailChange = {},
            onPassWordChange = {},
            onRegister = {},
            onNavigateBack = {}
        )
    }
}