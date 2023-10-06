package lk.lnbti.contactlist.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
    override suspend fun getContact(contactId: Int?): Contact? {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                contactDao.findById(contactId)
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
    override suspend fun addContact(contact: Contact): Long {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                try {
                    contactDao.insert(contact)
                } catch (e: Exception) {
                    0
                }
            }.await()
        }
    }

    /**
     * Updates an existing contact with new information.
     *
     * @param updatedContact The updated [Contact] information.
     */
    override suspend fun updateContact(updatedContact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.update(updatedContact)
        }
    }

    /**
     * Deletes a contact by its id.
     *
     * @param contactId The id of the contact to be deleted.
     */
    override suspend fun deleteContact(contactId: Int) {
        withContext(Dispatchers.IO) {
            val contact = contactDao.findById(contactId)
            contact?.let {
                contactDao.delete(it)
            }
        }
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
    override suspend fun loadAllContacts(): List<Contact> {
        return withContext(Dispatchers.IO) {
            return@withContext contactDao.getAllContacts()
        }
    }
}