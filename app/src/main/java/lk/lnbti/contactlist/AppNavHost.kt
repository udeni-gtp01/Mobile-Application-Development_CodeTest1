package lk.lnbti.contactlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lk.lnbti.contactlist.ui.view.AddContactScreen
import lk.lnbti.contactlist.ui.view.ContactInfoScreen
import lk.lnbti.contactlist.ui.view.ContactListScreen
import lk.lnbti.contactlist.ui.view.EditContactScreen

/**
 * Composable function responsible for hosting the navigation flow of the app.
 *
 * @param navController The [NavHostController] used for navigation between screens.
 */
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ContactList.route) {
        // Contact List Screen
        composable(route = ContactList.route) {
            ContactListScreen(
                onContactItemClicked = { contactId ->
                    navController.navigateToContactInfo(contactId)
                },
                onNewContactClicked = { navController.navigateSingleTopTo(AddContact.route) }
            )
        }
// Contact Info Screen
        composable(
            route = ContactInfo.routeWithArgs,
            arguments = ContactInfo.arguments,
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val contactId =
                navBackStackEntry.arguments?.getInt(ContactInfo.contactIdArg)
            // Pass contact id to ContactInfoScreen
            ContactInfoScreen(
                contactId = contactId,
                onCancelButtonClicked = { navController.navigateSingleTopTo(ContactList.route) },
                onDeleteButtonClicked = { navController.navigateSingleTopTo(ContactList.route) },
                onEditButtonClicked = { contactId ->
                    navController.navigateToEditContact(contactId)
                },
            )
        }
        // Edit Contact Screen
        composable(
            route = EditContact.routeWithArgs,
            arguments = EditContact.arguments,
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val contactId =
                navBackStackEntry.arguments?.getInt(EditContact.contactIdArg)
            // Pass contact id to ContactInfoScreen
            EditContactScreen(
                contactId = contactId,
                onCancelButtonClicked = { contactId ->
                    navController.navigateToContactInfo(contactId = contactId)
                },
                onSaveButtonClicked = { contactId ->
                    navController.navigateToContactInfo(contactId)
                },
            )
        }
        // Add Contact Screen
        composable(route = AddContact.route) {
            AddContactScreen(
                onSaveButtonClicked = { contactId ->
                    navController.navigateToContactInfo(contactId)
                },
                onCancelButtonClicked = { navController.navigateSingleTopTo(ContactList.route) }
            )
        }
    }
}

// Extension function to navigate to Contact Info screen
private fun NavHostController.navigateToContactInfo(contactId: Int) {
    this.navigateSingleTopTo("${ContactInfo.route}/$contactId")
}

// Extension function to navigate to Edit Contact screen
private fun NavHostController.navigateToEditContact(contactId: Int) {
    this.navigateSingleTopTo("${EditContact.route}/$contactId")
}

// Extension function to navigate with single top behavior
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
        restoreState = false
    }

