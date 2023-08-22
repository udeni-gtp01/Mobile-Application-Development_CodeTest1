package lk.lnbti.contactlist.data

import android.util.Log

data class Contact(
    var name: String,
    var phone: String,
)

object ContactData {
    val contacts: MutableList<Contact> = mutableListOf(
        Contact(
            name = "Udeni",
            phone = "1234567890"
        ),
        Contact(
            name = "Sara",
            phone = "0784567890"
        ),
        Contact(
            name = "Nimal",
            phone = "0733456789"
        ),
        Contact(
            name = "Gayani",
            phone = "0774567890"
        ),
        Contact(
            name = "San",
            phone = "0714567890"
        ),
        Contact(
            name = "Dee",
            phone = "0744567890"
        ),
        Contact(
            name = "Nuwan",
            phone = "1234567890"
        ),
        Contact(
            name = "Mali",
            phone = "0784567890"
        ),
        Contact(
            name = "Raj",
            phone = "0733456789"
        ),
    )

    fun getContact(contactName: String?): Contact? {
        try {
            return contacts.first { it.name == contactName }
        } catch (e: Exception) {
            Log.d("Search contact",e.message.toString())
            return null
        }
    }

    fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    fun updateContact(originalContactName: String, updatedContact: Contact) {
        var contact = contacts.find { it.name == originalContactName }
        contact?.let {
            it.name = updatedContact.name
            it.phone = updatedContact.phone
        }
    }

    fun deleteContact(contactName: String) {
        contacts.remove(contacts.first { it.name == contactName })
    }
}

