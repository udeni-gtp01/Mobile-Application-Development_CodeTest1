package lk.lnbti.contactlist.data

import androidx.compose.runtime.Immutable

@Immutable
data class Contact(
    val name: String,
    val phone: String,
)

object ContactData{
    val contacts:List<Contact> = listOf(
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
}