package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Home(navController: NavHostController, database: AppDatabase) {

    val menuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    Column {
        MaterialTopBar(navController = navController)
        HeroSection()
        FilterSection(menuItems)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTopBar(navController: NavHostController) {
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
}

@Composable
fun HeroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.restaurant_name),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 24.sp,
            color = LittleLemonColor.cloud
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                color = LittleLemonColor.cloud,
                modifier = Modifier.fillMaxWidth(0.75f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

@Composable
fun FilterSection(menuItems: List<MenuItemRoom>) {

    var searchPhrase by remember {
        mutableStateOf("")
    }

    var selectedFilter by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.green)
    ) {
        OutlinedTextField(
            value = searchPhrase,
            onValueChange = {
                searchPhrase = it
            },
            placeholder = { Text(stringResource(id = R.string.search_hint)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_bar_icon)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .clip(shape = RoundedCornerShape((10.dp)))
                .background(color = LittleLemonColor.cloud)
        )
    }
    var menuItemsFiltered = menuItems

    if (searchPhrase.isNotEmpty()) {
        menuItemsFiltered =
            menuItemsFiltered.filter { it.title.contains(searchPhrase, ignoreCase = true) }
    }


    Column {
        Text(
            text = stringResource(id = R.string.order_delivery).uppercase(),
            modifier = Modifier.padding(15.dp, 20.dp, 0.dp, 20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = { selectedFilter = "starters" },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.starters),
                    color = LittleLemonColor.charcoal
                )
            }
            Button(
                onClick = { selectedFilter = "mains" },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.mains),
                    color = LittleLemonColor.charcoal
                )
            }
            Button(
                onClick = { selectedFilter = "desserts" },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.desserts),
                    color = LittleLemonColor.charcoal
                )
            }
            Button(
                onClick = { selectedFilter = "drinks" },
                colors = ButtonDefaults.buttonColors(
                    LittleLemonColor.cloud
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.drinks),
                    color = LittleLemonColor.charcoal
                )
            }
        }
    }

    if (selectedFilter.isNotEmpty()) {
        menuItemsFiltered =
            menuItemsFiltered.filter { it.category.contains(selectedFilter, ignoreCase = true) }
    }


    MenuItems(menuItemsFiltered)

}


@Composable
private fun MenuItems(menuItems: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = menuItems,
            itemContent = {
                MenuItem(it)
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItem(menuItem: MenuItemRoom) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = menuItem.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = menuItem.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(top = 5.dp, bottom = 5.dp)
            )
            Text(
                text = "$${menuItem.price}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        GlideImage(
            model = menuItem.image,
            contentDescription = menuItem.title,
            modifier = Modifier
                .clip(shape = RoundedCornerShape((10.dp)))
        )
    }
    Divider(color = LittleLemonColor.charcoal, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {

    val menuItemsDummy = listOf(
        MenuItemRoom(
            1,
            "Greek Salad",
            "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            10.0,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            "starters"
        )
    )
    Column {
        MaterialTopBar(rememberNavController())
        HeroSection()
        //FilterSection(menuItems = menuItemsDummy) //glide image does not work well with preview
    }
}
