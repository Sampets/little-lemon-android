package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Profile(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences by lazy { context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE) }

    val firstName = sharedPreferences.getString(FIRST_NAME, "Unknown") ?: ""
    val lastName = sharedPreferences.getString(LAST_NAME, "Unknown") ?: ""
    val email = sharedPreferences.getString(EMAIL, "Unknown") ?: ""

    Column {
        TopBar()
        Text(
            text = stringResource(id = R.string.profile_welcome),
            fontSize = 20.sp,
            modifier = Modifier.padding(30.dp, 60.dp, 0.dp, 40.dp)
        )
        Text(
            text = stringResource(id = R.string.first_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 20.dp, 30.dp, 0.dp)
        )
        Text(
            text = "  $firstName",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .border(BorderStroke(1.dp, LittleLemonColor.charcoal), RoundedCornerShape(30))
                .padding(vertical = 5.dp),
        )
        Text(
            text = stringResource(id = R.string.last_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 20.dp, 30.dp, 0.dp)
        )
        Text(
            text = "  $lastName",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .border(BorderStroke(1.dp, LittleLemonColor.charcoal), RoundedCornerShape(30))
                .padding(vertical = 5.dp),
        )
        Text(
            text = stringResource(id = R.string.email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 20.dp, 30.dp, 0.dp)
        )
        Text(
            text = "  $email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .border(BorderStroke(1.dp, LittleLemonColor.charcoal), RoundedCornerShape(30))
                .padding(vertical = 5.dp),
        )
        Button(
            onClick =
            {
                sharedPreferences.edit().clear().apply()
                Toast.makeText(context, R.string.logged_out, Toast.LENGTH_SHORT).show()
                navController.navigate(Onboarding.route) {
                    popUpTo(Home.route) {
                        inclusive = true
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
                text = stringResource(id = R.string.log_out),
                color = LittleLemonColor.charcoal,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(rememberNavController())
}