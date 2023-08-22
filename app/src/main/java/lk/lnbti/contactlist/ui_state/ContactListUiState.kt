package lk.lnbti.contactlist.ui_state

import lk.lnbti.contactlist.data.Contact

/**
 * Represents the UI state for the contact list.
 *
 * @property contactList The list of contacts to be displayed in the UI.
 */
data class ContactListUiState(
    val contactList: List<Contact> = emptyList()
)