package lk.lnbti.contactlist

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val route: String
}

object ContactList : AppDestination {
    override val route = "contact_list"
}

object ContactInfo : AppDestination {
    override val route = "contact_info"
    const val contactNameArg = "contact_name"
    val routeWithArgs = "$route/{$contactNameArg}"
    val arguments = listOf(
        navArgument(contactNameArg) { type = NavType.StringType }
    )
}

object AddContact : AppDestination {
    override val route = "add_contact"
}

object EditContact : AppDestination {
    override val route = "edit_contact"
    const val contactNameArg = "contact_name"
    val routeWithArgs = "$route/{$contactNameArg}"
    val arguments = listOf(
        navArgument(contactNameArg) { type = NavType.StringType }
    )
}