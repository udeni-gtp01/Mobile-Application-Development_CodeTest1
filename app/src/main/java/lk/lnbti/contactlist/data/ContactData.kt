package lk.lnbti.contactlist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a contact with a name and phone number.
 *
 * @property name The name of the contact.
 * @property phone The phone number of the contact.
 */
@Entity(tableName = "contact_table")
data class Contact(
    var name: String,
    var phone: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

/**
 * A singleton object that manages a list of contacts.
 */
object ContactData {
    /**
     * The list of contacts stored in memory.
     */
    val contacts: MutableList<Contact> = mutableListOf(
        Contact(
            name = "Udeni", phone = "1234567890"
        ),
        Contact(
            name = "Sara", phone = "0784567890"
        ),
        Contact(
            name = "Nimal", phone = "0733456789"
        ),
        Contact(
            name = "Gayani", phone = "0774567890"
        ),
        Contact(
            name = "San", phone = "0714567890"
        ),
        Contact(
            name = "Dee", phone = "0744567890"
        ),
        Contact(
            name = "Nuwan", phone = "1234567890"
        ),
        Contact(
            name = "Mali", phone = "0784567890"
        ),
        Contact(
            name = "Raj", phone = "0733456789"
        ),
    )
}

