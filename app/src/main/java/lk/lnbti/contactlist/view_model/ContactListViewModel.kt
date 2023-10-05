package lk.lnbti.contactlist.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService
import lk.lnbti.contactlist.ui_state.ContactListUiState

/**
 * ViewModel class responsible for managing the UI state of the Contact List screen.
 */
class ContactListViewModel(
    private val contactService: ContactService
) :
    ViewModel() {

    val contactList: LiveData<List<Contact>> = ContactListUiState.contactList
    var searchQuery by mutableStateOf("")

    /**
     * Initializes the ViewModel and loads the initial contact list.
     */
    init {
        loadContactList()
    }

    /**
     * Loads the initial contact list and updates the UI state.
     */
    fun loadContactList() {
        viewModelScope.launch {
            ContactListUiState.loadContactList(contactService.loadAllContacts())
        }
    }

    /**
     * Search contacts based on the given query and update the UI state with the filtered results.
     *
     * @param query The search query to filter contacts by.
     */
    fun searchContacts(query: String) {
        searchQuery = query
        contactService.searchContacts(searchQuery)
    }
}