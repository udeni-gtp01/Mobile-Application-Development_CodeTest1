package lk.lnbti.contactlist.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lk.lnbti.contactlist.dao.ContactDao
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData
import lk.lnbti.contactlist.ui_state.ContactListUiState


class ContactServiceImpl(private val contactDao: ContactDao) : ContactService {
    /**
     * Retrieves a contact by its name.
     *
     * @param contactName The name of the contact to retrieve.
     * @return The [Contact] instance with the matching name, or null if not found.
     */
    override suspend fun getContact(contactName: String?): Contact? {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                contactDao.findByName(contactName)
               // ContactData.contacts.first { it.name == contactName }
            } catch (e: Exception) {
                null
            }
        }

    }

    /**
     * Adds a new contact to the list.
     *
     * @param contact The [Contact] instance to be added.
     */
    override fun addContact(contact: Contact) {
        ContactData.contacts.add(contact)
        ContactListUiState.loadContactList(ContactData.contacts)
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
        ContactListUiState.loadContactList(ContactData.contacts)
    }

    /**
     * Deletes a contact by its name.
     *
     * @param contactName The name of the contact to be deleted.
     */
    override suspend fun deleteContact(contactName: String) {
        withContext(Dispatchers.IO) {
            val contact : Contact?=contactDao.findByName(contactName)
            contact?.let { contactDao.delete(it) }
        }
        ContactListUiState.loadContactList(loadAllContacts())
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
        ContactListUiState.loadContactList(filteredContacts)
    }

    /**
     * Retrieve all contacts and update the UI state.
     *
     */
    override suspend fun loadAllContacts():List<Contact> {
        return withContext(Dispatchers.IO) {
            return@withContext contactDao.getAllContacts()
        }
    }
}