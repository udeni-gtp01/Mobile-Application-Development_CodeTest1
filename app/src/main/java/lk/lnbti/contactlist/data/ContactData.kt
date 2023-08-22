package lk.lnbti.contactlist.data

import androidx.compose.runtime.Immutable

data class Contact(
    var name: String,
    var phone: String,
)

object ContactData{
    val contacts:MutableList<Contact> = mutableListOf(
        Contact(
            name = "Udeni",
            phone = "12345"
        ),
        Contact(
            name = "Sunil",
            phone = "67890"
        ),
        Contact(
            name = "Nimal",
            phone = "12345"
        ),
        Contact(
            name = "Gayani",
            phone = "67890"
        ),
        Contact(
            name = "San",
            phone = "12345"
        ),
        Contact(
            name = "Dee",
            phone = "67890"
        )
    )
    fun getContact(contactId: String?): Contact {
        return contacts.first { it.name == contactId }
    }
    fun addContact(contact:Contact) {
        contacts.add(contact)
    }

    fun updateContact(originalContactName:String,newContact:Contact) {
        var contact=contacts.find { it.name==originalContactName }
        contact?.let {
            it.name=newContact.name
            it.phone=newContact.phone
        }
    }
}

