package lk.lnbti.contactlist.view_model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

class AddContactViewModel : ViewModel() {
    var newContactName by mutableStateOf("")
    var newContactPhone by mutableStateOf("")

    var isValidContactName by mutableStateOf(false)
    var isValidPhone by mutableStateOf(false)

    private fun validateContactName() {
        isValidContactName = !newContactName.isBlank()
    }

    private fun validatePhone() {
        isValidPhone =
            !(newContactPhone.isBlank() || newContactPhone.length != 10 || !newContactPhone.isDigitsOnly())
    }

    fun updateContactName(contactName: String) {
        newContactName = contactName
        validateContactName()
    }

    fun updateContactPhone(contactPhone: String) {
        newContactPhone = contactPhone
        validatePhone()
    }

    fun saveNewContact(): String {
        ContactData.addContact(Contact(newContactName, newContactPhone))
        val newContactName = newContactName
        resetNewContact()
        return newContactName
    }

    fun resetNewContact() {
        newContactName = ""
        newContactPhone = ""
    }
}