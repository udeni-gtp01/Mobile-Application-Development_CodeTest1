package lk.lnbti.contactlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lk.lnbti.contactlist.ui.view.ContactInfoScreen
import lk.lnbti.contactlist.ui.view.ContactListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ContactList.route){
        composable(route = ContactList.route){
            ContactListScreen()
        }

        composable(route = ContactInfo.route){
            ContactInfoScreen()
        }
    }
}
fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) { launchSingleTop = true }