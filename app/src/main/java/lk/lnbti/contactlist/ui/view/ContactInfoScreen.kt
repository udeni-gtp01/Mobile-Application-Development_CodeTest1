package lk.lnbti.contactlist.ui.view
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import lk.lnbti.contactlist.data.ContactData

@Composable
fun ContactInfoScreen(
contactId:String?=ContactData.contacts.first().name
){
    val contact = remember(contactId) { ContactData.getContact(contactId) }
    Row {
        Text(text = "${contact.name} ${contact.phone}")
    }
}