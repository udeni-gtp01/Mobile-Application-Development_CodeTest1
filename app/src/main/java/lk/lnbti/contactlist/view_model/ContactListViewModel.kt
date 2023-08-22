package lk.lnbti.contactlist.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import lk.lnbti.contactlist.data.ContactData
import lk.lnbti.contactlist.ui_state.ContactListUiState

class ContactListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ContactListUiState())
    val uiState: StateFlow<ContactListUiState> = _uiState.asStateFlow()

    init {
        loadContactList()
    }

    private fun loadContactList() {
        _uiState.value = ContactListUiState(contactList = ContactData.contacts)
    }
}