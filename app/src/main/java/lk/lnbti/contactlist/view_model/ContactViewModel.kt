package lk.lnbti.contactlist.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

class ContactViewModel() : ViewModel() {
    private var originalContactName: String = ""
    private var originalContactPhone: String = ""
    var newContactName by mutableStateOf("")
    var newContactPhone by mutableStateOf("")
    var isContactEditable by mutableStateOf(false)
    var isContactClosed by mutableStateOf(false)

    fun updateContactName(contactName: String) {
        newContactName = contactName
    }

    fun updateContactPhone(contactPhone: String) {
        newContactPhone = contactPhone
    }

    fun findContact(contactName: String?) {
        contactName?.let {
            if (originalContactName == "")originalContactName = contactName
            originalContactName?.let {
                Log.d("oyasumi", "finding $it")
                val contact = ContactData.getContact(it)
                originalContactName = contact.name
                originalContactPhone = contact.phone
                newContactName = originalContactName
                newContactPhone = originalContactPhone
            }
        }

    }

    fun saveNewContact() {
        Log.d("oyasumi", "save contact $newContactName")
        ContactData.addContact(Contact(newContactName, newContactPhone))
        isContactEditable = false
    }

    fun updateContact() {
        ContactData.updateContact(
            originalContactName = originalContactName,
            Contact(newContactName, newContactPhone)
        )

        Log.d("oyasumi", "update contact $newContactName")
        originalContactName = newContactName
        originalContactPhone = newContactPhone
        isContactEditable = false
    }

    fun enableEditContact() {
        isContactEditable = true
    }
    fun cancelEditContact() {
        isContactEditable = false
    }

    fun resetContact() {
        originalContactName = ""
        originalContactPhone = ""
        newContactName = ""
        newContactPhone = ""
        isContactEditable = false
        isContactClosed = true
    }
}