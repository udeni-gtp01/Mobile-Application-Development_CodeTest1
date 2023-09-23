package lk.lnbti.contactlist.view_model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService
import lk.lnbti.contactlist.service.ContactServiceImpl
import lk.lnbti.contactlist.ui_state.ContactListUiState

/**
 * ViewModel class responsible for managing the UI state and interactions related to adding a new contact.
 */
class AddContactViewModel(
    private val contactService: ContactService = ContactServiceImpl.getInstance(),
) :
    ViewModel() {

    // Mutable state properties to hold new contact's name and phone number
    var newContactName by mutableStateOf("")
    var newContactPhone by mutableStateOf("")

    // Mutable state properties to track the validity of the contact name and phone number
    var isContactNameError by mutableStateOf(false)
    var isPhoneError by mutableStateOf(false)

    fun isValidationSuccessful():Boolean{
        validateContactName()
        validatePhone()
        return (!isContactNameError && !isPhoneError)
    }

    /**
     * Validates the entered contact name and updates the [isValidContactName] property accordingly.
     */
    private fun validateContactName() {
        isContactNameError = newContactName.isBlank()
    }

    /**
     * Validates the entered contact phone number and updates the [isValidPhone] property accordingly.
     */
    private fun validatePhone() {
        isPhoneError =
            (newContactPhone.isBlank() || newContactPhone.length != 10 || !newContactPhone.all { it.isDigit() })
    }

    /**
     * Updates the [newContactName] property and triggers contact name validation.
     *
     * @param contactName The new contact name to update.
     */
    fun updateContactName(contactName: String) {
        newContactName = contactName
    }

    /**
     * Updates the [newContactPhone] property and triggers phone number validation.
     *
     * @param contactPhone The new contact phone number to update.
     */
    fun updateContactPhone(contactPhone: String) {
        newContactPhone = contactPhone
    }

    /**
     * Saves the new contact's information to the data source, resets the inputs, and returns the saved contact name.
     *
     * @return The name of the saved contact.
     */
    fun saveNewContact(): String {
        contactService.addContact(Contact(newContactName, newContactPhone))
        val newContactName = newContactName
        resetNewContact()
        return newContactName
    }

    /**
     * Resets the new contact's inputs to their initial state.
     */
    fun resetNewContact() {
        newContactName = ""
        newContactPhone = ""
    }
}