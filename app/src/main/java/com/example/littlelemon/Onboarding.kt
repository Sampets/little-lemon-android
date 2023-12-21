package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Onboarding(navController: NavHostController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences by lazy { context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) }

    Column {
        TopBar()
        Text(
            text = stringResource(id = R.string.onboarding_welcome),
            fontSize = 20.sp,
            color = LittleLemonColor.cloud,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .background(color = LittleLemonColor.green)
                .padding(vertical = 40.dp)
        )
        Text(
            text = stringResource(id = R.string.first_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 20.dp, 30.dp, 0.dp)
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            placeholder = { Text(text = stringResource(id = R.string.john)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )
        Text(
            text = stringResource(id = R.string.last_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 20.dp, 30.dp, 0.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            placeholder = { Text(text = stringResource(id = R.string.doe)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )
        Text(
            text = stringResource(id = R.string.email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 20.dp, 30.dp, 0.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = stringResource(id = R.string.example_email)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Button(
            onClick =
            {
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    Toast.makeText(context, R.string.registration_fail, Toast.LENGTH_SHORT).show()
                } else {
                    sharedPreferences.edit().putString(FIRST_NAME, firstName).apply()
                    sharedPreferences.edit().putString(LAST_NAME, lastName).apply()
                    sharedPreferences.edit().putString(EMAIL, email).apply()

                    Toast.makeText(context, R.string.registration_success, Toast.LENGTH_SHORT)
                        .show()

                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }

                }
            },
            colors = ButtonDefaults.buttonColors(
                LittleLemonColor.yellow
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 50.dp, 30.dp, 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.register),
                color = LittleLemonColor.charcoal,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(rememberNavController())
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(0.5f)
        )
    }
}