package lk.lnbti.contactlist.ui_state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lk.lnbti.contactlist.data.Contact

/**
 * Represents the UI state for the contact list.
 *
 * @property contactList The list of contacts to be displayed in the UI.
 */
object ContactListUiState{
    private val _contactList: MutableLiveData<List<Contact>> = MutableLiveData(emptyList())
    val contactList: LiveData<List<Contact>> = _contactList

    fun loadContactList(contactList: List<Contact>) {
        _contactList.value = contactList
    }
}