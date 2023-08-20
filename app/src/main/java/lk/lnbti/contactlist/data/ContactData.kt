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
            name = "Iresha",
            phone = "67890"
        )
    )
}