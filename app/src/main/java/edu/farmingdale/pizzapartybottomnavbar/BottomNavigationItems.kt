package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Welcome : BottomNavigationItems("welcomeScreen")

    object PizzaScreen : BottomNavigationItems(
        route = "pizzaScreen",
        title = "Pizza Order",
        icon = Icons.Outlined.ShoppingCart
    )
    object GpaAppScreen : BottomNavigationItems(
        route = "gpaappscreen",
        title = "Gpa App",
        icon = Icons.Default.CheckCircle
    )
    object Screen3 : BottomNavigationItems(
        route = "screen3",
        title = "Screen3",
        icon = Icons.Outlined.AccountCircle
    )
}
//While trying to figure out
// todo 8 my entire code crashed. Even after resetting, Was able to do 1-7 but failed 8