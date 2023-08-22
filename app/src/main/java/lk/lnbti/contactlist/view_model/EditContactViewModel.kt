package lk.lnbti.contactlist.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

class EditContactViewModel : ViewModel() {

    private var originalContactName: String = ""
    var updatedContactName by mutableStateOf("")
    var updatedContactPhone by mutableStateOf("")
    var isValidContactName by mutableStateOf(true)
    var isValidPhone by mutableStateOf(true)

    private fun validateContactName() {
        isValidContactName = !updatedContactName.isBlank()
    }
    private fun validatePhone() {
        isValidPhone =
            !(updatedContactPhone.isBlank() || updatedContactPhone.length!=10 || !updatedContactPhone.isDigitsOnly())
    }
    fun updateContactName(contactName: String) {
        updatedContactName = contactName
        validateContactName()
    }

    fun updateContactPhone(contactPhone: String) {
        updatedContactPhone = contactPhone
        validatePhone()
    }

    fun searchContact(contactName: String?) {
        contactName?.let {
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