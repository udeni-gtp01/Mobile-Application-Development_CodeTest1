package lk.lnbti.contactlist.service

import lk.lnbti.contactlist.data.Contact

interface ContactService {
    fun getContact(contactName: String?): Contact?
    fun addContact(contact: Contact)
    fun updateContact(originalContactName: String, updatedContact: Contact)
    fun deleteContact(contactName: String)
}