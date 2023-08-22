package lk.lnbti.contactlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import lk.lnbti.contactlist.ui.view.ContactInfoScreen
import lk.lnbti.contactlist.ui.view.ContactListScreen

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
object UpdateContact : AppDestination {
    override val route = "update_contact"
    const val contactNameArg = "contact_name"
    val routeWithArgs = "$route/{$contactNameArg}"
    val arguments = listOf(
        navArgument(contactNameArg) { type = NavType.StringType }
    )
}