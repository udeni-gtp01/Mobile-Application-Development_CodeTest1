package lk.lnbti.contactlist.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData
import lk.lnbti.contactlist.ui_state.ContactListUiState
import lk.lnbti.contactlist.ui_state.ContactUiState

class UpdateContactViewModel : ViewModel(){

    private lateinit var originalContactName:String
    var updatedContactName by mutableStateOf("")
    var updatedContactPhone by mutableStateOf("")
    var isUpdateContactDone by mutableStateOf(false)

    fun getContactByName(contactName:String?): Contact {
        val contact= ContactData.getContact(contactName)
        originalContactName=contact.name
        updatedContactName=contact.name
        updatedContactPhone=contact.phone
        return contact
    }
    fun updateContactName(contactName: String) {
        updatedContactName = contactName
    }

    fun updateContactPhone(contactPhone: String) {
        updatedContactPhone = contactPhone
    }

    fun updateContact() {
        ContactData.updateContact(originalContactName = originalContactName,Contact(updatedContactName, updatedContactPhone))
        isUpdateContactDone = true
    }
}