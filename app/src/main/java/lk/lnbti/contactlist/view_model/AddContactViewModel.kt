package lk.lnbti.contactlist.view_model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

class AddContactViewModel : ViewModel() {
    var newContactName by mutableStateOf("")
    var newContactPhone by mutableStateOf("")
    var isAddContactDone by mutableStateOf(false)

    fun updateContactName(contactName: String) {
        newContactName = contactName
    }

    fun updateContactPhone(contactPhone: String) {
        newContactPhone = contactPhone
    }

    fun saveNewContact() {
        ContactData.addContact(Contact(newContactName, newContactPhone))
        isAddContactDone = true
    }

    fun cancelNewContact() {
        newContactName = ""
        newContactPhone = ""
        isAddContactDone = true
    }
}