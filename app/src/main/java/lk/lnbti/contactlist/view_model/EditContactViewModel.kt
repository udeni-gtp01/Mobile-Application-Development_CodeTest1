package lk.lnbti.contactlist.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lk.lnbti.contactlist.ContactListApplication
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService

/**
 * ViewModel class responsible for managing the UI state and interactions related to editing a contact.
 */
class EditContactViewModel(private val contactService: ContactService = ContactListApplication().container.cantactService) :
    ViewModel() {

    // Original contact name to track changes
    private var originalContactName: String = ""

    // Mutable state properties to hold updated contact's name and phone number
    var updatedContactName by mutableStateOf("")
    var updatedContactPhone by mutableStateOf("")

    // Mutable state properties to track the validity of the updated contact name and phone number
    var isContactNameError by mutableStateOf(false)
    var isPhoneError by mutableStateOf(false)

    fun isValidationSuccessful(): Boolean {
        validateContactName()
        validatePhone()
        return (!isContactNameError && !isPhoneError)
    }

    /**
     * Validates the entered contact name and updates the [isContactNameError] property accordingly.
     */
    private fun validateContactName() {
        isContactNameError = updatedContactName.isBlank()
    }

    /**
     * Validates the entered contact phone number and updates the [isPhoneError] property accordingly.
     */
    private fun validatePhone() {
        isPhoneError =
            (updatedContactPhone.isBlank() || updatedContactPhone.length != 10 || !updatedContactPhone.all { it.isDigit() })
    }

    /**
     * Updates the [updatedContactName] property and triggers updated contact name validation.
     *
     * @param contactName The updated contact name to update.
     */
    fun updateContactName(contactName: String) {
        updatedContactName = contactName
    }

    /**
     * Updates the [updatedContactPhone] property and triggers updated phone number validation.
     *
     * @param contactPhone The updated contact phone number to update.
     */
    fun updateContactPhone(contactPhone: String) {
        updatedContactPhone = contactPhone
    }

    /**
     * Searches for the contact to edit using the provided contact name and updates the ViewModel's state accordingly.
     *
     * @param contactName The contact name to search for and edit.
     */
    fun searchContact(contactName: String?) {
        viewModelScope.launch {
            contactName?.let {
                var contact = contactService.getContact(contactName)
                contact?.let {
                    originalContactName = contact.name
                    updatedContactName = contact.name
                    updatedContactPhone = contact.phone
                }
            }
        }
    }

    /**
     * Saves the edited contact's information to the data source, resets the inputs, and returns the saved contact name.
     *
     * @return The name of the saved contact.
     */
    fun saveContact(): String {
        updatedContactName?.let {
            contactService.updateContact(
                originalContactName = originalContactName,
                updatedContact = Contact(updatedContactName, updatedContactPhone)
            )
        }
        originalContactName = updatedContactName
        return resetContact()
    }

    /**
     * Resets the edited contact's inputs to their initial state and returns the original contact name.
     *
     * @return The original contact name before editing.
     */
    fun resetContact(): String {
        var tempContactName = originalContactName
        updatedContactName = ""
        updatedContactPhone = ""
        originalContactName = ""
        return tempContactName
    }
}