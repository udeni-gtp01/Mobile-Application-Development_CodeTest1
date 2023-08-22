package lk.lnbti.contactlist.view_model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

/**
 * ViewModel class responsible for managing the UI state and interactions related to adding a new contact.
 */
class AddContactViewModel : ViewModel() {

    // Mutable state properties to hold new contact's name and phone number
    var newContactName by mutableStateOf("")
    var newContactPhone by mutableStateOf("")

    // Mutable state properties to track the validity of the contact name and phone number
    var isValidContactName by mutableStateOf(false)
    var isValidPhone by mutableStateOf(false)

    /**
     * Validates the entered contact name and updates the [isValidContactName] property accordingly.
     */
    private fun validateContactName() {
        isValidContactName = !newContactName.isBlank()
    }

    /**
     * Validates the entered contact phone number and updates the [isValidPhone] property accordingly.
     */
    private fun validatePhone() {
        isValidPhone =
            !(newContactPhone.isBlank() || newContactPhone.length != 10 || !newContactPhone.isDigitsOnly())
    }

    /**
     * Updates the [newContactName] property and triggers contact name validation.
     *
     * @param contactName The new contact name to update.
     */
    fun updateContactName(contactName: String) {
        newContactName = contactName
        validateContactName()
    }

    /**
     * Updates the [newContactPhone] property and triggers phone number validation.
     *
     * @param contactPhone The new contact phone number to update.
     */
    fun updateContactPhone(contactPhone: String) {
        newContactPhone = contactPhone
        validatePhone()
    }

    /**
     * Saves the new contact's information to the data source, resets the inputs, and returns the saved contact name.
     *
     * @return The name of the saved contact.
     */
    fun saveNewContact(): String {
        ContactData.addContact(Contact(newContactName, newContactPhone))
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