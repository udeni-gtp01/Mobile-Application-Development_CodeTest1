package lk.lnbti.contactlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lk.lnbti.contactlist.ui.view.AddContactScreen
import lk.lnbti.contactlist.ui.view.ContactInfoScreen
import lk.lnbti.contactlist.ui.view.ContactListScreen
import lk.lnbti.contactlist.ui.view.UpdateContactScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ContactList.route) {
        composable(route = ContactList.route) {
            ContactListScreen(
                onContactItemClicked = { contactName ->
                    navController.navigateToContactInfo(contactName)
                },
                onNewContactClicked = { navController.navigateSingleTopTo(AddContact.route) }
            )
        }

        composable(
            route = ContactInfo.routeWithArgs,
            arguments = ContactInfo.arguments,
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val contactName =
                navBackStackEntry.arguments?.getString(ContactInfo.contactNameArg)
            // Pass contactId to ContactInfoScreen
            ContactInfoScreen(
                contactName = contactName,
                navController = navController
            )
        }
        composable(route = AddContact.route) {
            AddContactScreen(navController)
        }
        composable(
            route = UpdateContact.routeWithArgs,
            arguments = UpdateContact.arguments
        ) { navBackStackEntry ->
            val contactName = navBackStackEntry.arguments?.getString(UpdateContact.contactNameArg)
            UpdateContactScreen(navController = navController, contactName = contactName)
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
        restoreState = true
    }

private fun NavHostController.navigateToContactInfo(contactName: String) {
    this.navigateSingleTopTo("${ContactInfo.route}/$contactName")
}

private fun NavHostController.navigateToEditContact(contactName: String) {
    this.navigateSingleTopTo("${UpdateContact.route}/$contactName")
}

private fun NavigateToContactList(
    navController: NavHostController
) {
    navController.popBackStack(ContactList.route, inclusive = false)
}