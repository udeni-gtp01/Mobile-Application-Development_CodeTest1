package lk.lnbti.contactlist.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService
import lk.lnbti.contactlist.ui_state.ContactListUiState

/**
 * ViewModel class responsible for managing the UI state and
 * interactions related to a specific contact's information.
 */
class ContactInfoViewModel(private val contactService: ContactService) :
    ViewModel() {

    // Mutable state that holds the current contact information being displayed
    var contact by mutableStateOf<Contact?>(null)

    /**
     * Searches for the contact with the given unique id and updates the [contact] property if found.
     *
     * @param contactId The unique id of the contact to search for.
     */
    fun searchContact(contactId: Int?) {
        viewModelScope.launch {
            contactId?.let {
                contact = contactService.getContact(contactId.toInt())
            }
        }

    }

    /**
     * Deletes the currently displayed contact from the data source.
     */
    fun deleteContact() {
        viewModelScope.launch {
            contact?.let {
                contactService.deleteContact(
                    contactName = it.name,
                )
                ContactListUiState.loadContactList(contactService.loadAllContacts())
            }
            contact = null
        }
    }
}