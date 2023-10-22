package lk.lnbti.contactlist.service

import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData
import lk.lnbti.contactlist.ui_state.ContactListUiState


object ContactServiceImpl : ContactService {

    private var instance: ContactServiceImpl? = null
    fun getInstance(): ContactServiceImpl {
        if (instance == null) {
            instance = ContactServiceImpl
        }
        return instance!!
    }

    /**
     * Retrieves a contact by its name.
     *
     * @param contactName The name of the contact to retrieve.
     * @return The [Contact] instance with the matching name, or null if not found.
     */
    override fun getContact(contactName: String?): Contact? {
        return try {
            ContactData.contacts.first { it.name == contactName }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Adds a new contact to the list.
     *
     * @param contact The [Contact] instance to be added.
     */
    override fun addContact(contact: Contact) {
        ContactData.contacts.add(contact)
        ContactListUiState.loadLectureList(ContactData.contacts)
    }

    /**
     * Updates an existing contact with new information.
     *
     * @param originalContactName The name of the contact to be updated.
     * @param updatedContact The updated [Contact] information.
     */
    override fun updateContact(originalContactName: String, updatedContact: Contact) {
        var contact = ContactData.contacts.find { it.name == originalContactName }
        contact?.let {
            it.name = updatedContact.name
            it.phone = updatedContact.phone
        }
        ContactListUiState.loadLectureList(ContactData.contacts)
    }

    /**
     * Deletes a contact by its name.
     *
     * @param contactName The name of the contact to be deleted.
     */
    override fun deleteContact(contactName: String) {
        ContactData.contacts.remove(ContactData.contacts.first { it.name == contactName })
        ContactListUiState.loadLectureList(ContactData.contacts)
    }

    /**
     * Search contacts based on the given query and update the UI state with the filtered results.
     *
     * @param query The search query to filter contacts by.
     */
    override fun searchContacts(query: String) {
        val filteredContacts = ContactData.contacts.filter {
            it.name.contains(query, ignoreCase = true)
        }
        ContactListUiState.loadLectureList(filteredContacts)
    }

    /**
     * Retrieve all contacts and update the UI state.
     *
     */
    override fun loadAllContacts() {
        ContactListUiState.loadLectureList(ContactData.contacts)
    }
}