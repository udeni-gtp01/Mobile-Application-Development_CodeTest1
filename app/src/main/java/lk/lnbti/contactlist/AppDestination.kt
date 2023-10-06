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
    const val contactIdArg = "contact_id"
    val routeWithArgs = "$route/{$contactIdArg}"
    val arguments = listOf(
        navArgument(contactIdArg) { type = NavType.IntType }
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
    const val contactIdArg = "contact_id"
    val routeWithArgs = "$route/{$contactIdArg}"
    val arguments = listOf(
        navArgument(contactIdArg) { type = NavType.IntType }
    )
}