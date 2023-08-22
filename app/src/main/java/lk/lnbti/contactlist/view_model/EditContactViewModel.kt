package lk.lnbti.contactlist.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

class EditContactViewModel : ViewModel() {

    private var originalContactName: String = ""
    var updatedContactName by mutableStateOf("")
    var updatedContactPhone by mutableStateOf("")

    fun updateContactName(contactName: String) {
        updatedContactName = contactName
    }

    fun updateContactPhone(contactPhone: String) {
        updatedContactPhone = contactPhone
    }

    fun searchContact(contactName: String?) {
        contactName?.let {
            //if (originalContactName == "") originalContactName = contactName
            Log.d("oyasumi", "searching in editvm $it")
            var contact = ContactData.getContact(it)
            contact?.let {
                originalContactName = contact.name
                updatedContactName = contact.name
                updatedContactPhone = contact.phone
            }

        }
    }

    fun saveContact(): String {
        updatedContactName?.let {
            ContactData.updateContact(
                originalContactName = originalContactName,
                updatedContact = Contact(updatedContactName, updatedContactPhone)
            )

            Log.d("oyasumi", "updated contact ${updatedContactName}")
        }
        originalContactName = updatedContactName
        return resetContact()
    }

    fun resetContact(): String {
        var tempContactName = originalContactName
        updatedContactName = ""
        updatedContactPhone = ""
        originalContactName = ""
        return tempContactName
    }
}