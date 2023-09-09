package lk.lnbti.contactlist.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService
import lk.lnbti.contactlist.service.ContactServiceImpl

/**
 * ViewModel class responsible for managing the UI state and
 * interactions related to a specific contact's information.
 */
class ContactInfoViewModel(private val contactService: ContactService = ContactServiceImpl()) :
    ViewModel() {

    // Mutable state that holds the current contact information being displayed
    var contact by mutableStateOf<Contact?>(null)

    /**
     * Searches for the contact with the given name and updates the [contact] property if found.
     *
     * @param contactName The name of the contact to search for.
     */
    fun searchContact(contactName: String?) {
        contactName?.let {
            contact = contactService.getContact(contactName)
        }
    }

    /**
     * Deletes the currently displayed contact from the data source.
     */
    fun deleteContact() {
        contact?.let {
            contactService.deleteContact(
                contactName = it.name,
            )
        }
        contact=null
    }
}