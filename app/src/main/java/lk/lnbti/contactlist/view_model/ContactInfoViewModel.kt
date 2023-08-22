package lk.lnbti.contactlist.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

class ContactInfoViewModel : ViewModel() {

    var contact by mutableStateOf<Contact?>(null)
    fun searchContact(contactName: String?) {
        contactName?.let {
            contact = ContactData.getContact(it)
        }
    }

    fun deleteContact() {
        contact?.let {
            ContactData.deleteContact(
                contactName = it.name,
            )
        }
    }
}