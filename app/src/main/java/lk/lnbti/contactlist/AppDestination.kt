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
    const val contactIdArg = "contact_id"
    val routeWithArgs = "$route/{$contactIdArg}"
    val arguments = listOf(
        navArgument(contactIdArg) { type = NavType.StringType }
    )
}