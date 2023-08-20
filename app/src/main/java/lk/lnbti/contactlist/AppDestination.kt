package lk.lnbti.contactlist

import androidx.compose.runtime.Composable
import lk.lnbti.contactlist.ui.view.ContactInfoScreen
import lk.lnbti.contactlist.ui.view.ContactListScreen

interface AppDestination {
    val route: String
    val screen: @Composable () -> Unit
}

object ContactList : AppDestination {
    override val route = "contact_list"
    override val screen: @Composable () -> Unit = { ContactListScreen() }
}

object ContactInfo : AppDestination {
    override val route = "contact_info"
    override val screen: @Composable () -> Unit = { ContactInfoScreen() }
}