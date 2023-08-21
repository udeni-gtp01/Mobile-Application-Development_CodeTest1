package lk.lnbti.contactlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lk.lnbti.contactlist.ui.view.AddContactScreen
import lk.lnbti.contactlist.ui.view.ContactInfoScreen
import lk.lnbti.contactlist.ui.view.ContactListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ContactList.route) {
        composable(route = ContactList.route) {
            ContactListScreen(
                onContactItemClicked = {contactId->
                    navController.navigateToContactInfo(contactId)
                },
                onNewContactClicked = {navController.navigateSingleTopTo(AddContact.route)}
            )
        }

        composable(
            route = ContactInfo.routeWithArgs,
            arguments = ContactInfo.arguments,
           // deepLinks = ContactInfo.deepLinks
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val contactId =
                navBackStackEntry.arguments?.getString(ContactInfo.contactIdArg)
            // Pass contactId to ContactInfoScreen
            ContactInfoScreen(contactId)
        }
        composable(route = AddContact.route) {
            AddContactScreen(navController)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true }

private fun NavHostController.navigateToContactInfo(contactId: String) {
    this.navigateSingleTopTo("${ContactInfo.route}/$contactId")
}