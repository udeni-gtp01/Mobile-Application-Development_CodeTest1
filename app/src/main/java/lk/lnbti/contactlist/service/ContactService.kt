package lk.lnbti.contactlist.service

import lk.lnbti.contactlist.data.Contact

/**
 * A service interface responsible for managing contact-related operations.
 */
interface ContactService {
    /**
     * Retrieves a contact by its name.
     *
     * @param contactName The name of the contact to retrieve.
     * @return The contact with the specified name, or null if not found.
     */
    fun getContact(contactName: String?): Contact?

    /**
     * Adds a new contact to the contact list.
     *
     * @param contact The contact to be added.
     */
    fun addContact(contact: Contact)

    /**
     * Updates an existing contact's information.
     *
     * @param originalContactName The name of the contact to update.
     * @param updatedContact The updated contact information.
     */
    fun updateContact(originalContactName: String, updatedContact: Contact)

    /**
     * Deletes a contact from the contact list by name.
     *
     * @param contactName The name of the contact to delete.
     */
    fun deleteContact(contactName: String)

    /**
     * Searches for contacts that match the given query.
     *
     * @param query The search query to filter contacts by.
     * @return A list of contacts that match the query.
     */
    fun searchContacts(query: String): List<Contact>
}