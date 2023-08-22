package lk.lnbti.contactlist

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Represents a destination in the app's navigation graph.
 */
interface AppDestination {
    /**
     * The route associated with the destination.
     */
    val route: String
}

/**
 * Represents the contact list screen destination.
 */
object ContactList : AppDestination {
    override val route = "contact_list"
}

/**
 * Represents the contact information screen destination.
 */
object ContactInfo : AppDestination {
    override val route = "contact_info"
    const val contactNameArg = "contact_name"
    val routeWithArgs = "$route/{$contactNameArg}"
    val arguments = listOf(
        navArgument(contactNameArg) { type = NavType.StringType }
    )
}

/**
 * Represents the add new contact screen destination.
 */
object AddContact : AppDestination {
    override val route = "add_contact"
}

/**
 * Represents the edit contact screen destination.
 */
object EditContact : AppDestination {
    override val route = "edit_contact"
    const val contactNameArg = "contact_name"
    val routeWithArgs = "$route/{$contactNameArg}"
    val arguments = listOf(
        navArgument(contactNameArg) { type = NavType.StringType }
    )
}