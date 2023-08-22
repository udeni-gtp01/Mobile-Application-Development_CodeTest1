package lk.lnbti.contactlist.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import lk.lnbti.contactlist.data.ContactData
import lk.lnbti.contactlist.ui_state.ContactListUiState

/**
 * ViewModel class responsible for managing the UI state of the Contact List screen.
 */
class ContactListViewModel : ViewModel() {

    // Internal mutable state flow to hold the UI state
    private val _uiState = MutableStateFlow(ContactListUiState())

    /**
     * Immutable state flow that exposes the current UI state to observers.
     */
    val uiState: StateFlow<ContactListUiState> = _uiState.asStateFlow()

    /**
     * Initializes the ViewModel and loads the initial contact list.
     */
    init {
        loadContactList()
    }

    /**
     * Loads the initial contact list and updates the UI state.
     */
    private fun loadContactList() {
        _uiState.value = ContactListUiState(contactList = ContactData.contacts)
    }
}