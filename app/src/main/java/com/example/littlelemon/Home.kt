package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {

    Column {
        CenterAlignedTopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(0.5f)
                )
            },
            actions = {
                IconButton(onClick = { navController.navigate(Profile.route) }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(48.dp)
                    )
                }
            },
        )
        Text(
            text = "Test - put content below ",
            color = LittleLemonColor.charcoal,
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(rememberNavController())
}

@Composable
fun HomeBar(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Profile Image",
                modifier = Modifier.size(48.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(0.5f)
        )
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}