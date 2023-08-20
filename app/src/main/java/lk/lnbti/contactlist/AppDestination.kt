package lk.lnbti.contactlist

import androidx.compose.runtime.Composable
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
}