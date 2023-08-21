package lk.lnbti.contactlist.ui_state

import lk.lnbti.contactlist.data.Contact

data class ContactListUiState(
    val contactList : List<Contact> = emptyList()
)