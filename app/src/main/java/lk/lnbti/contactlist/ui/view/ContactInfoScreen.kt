package lk.lnbti.contactlist.ui.view
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import lk.lnbti.contactlist.data.ContactData
import lk.lnbti.contactlist.view_model.ContactInfoViewModel

@Composable
fun ContactInfoScreen(
contactId:String?,
contactInfoViewModel: ContactInfoViewModel= viewModel()
){
    val contact = remember(contactId) { contactInfoViewModel.getContactByName(contactId) }
    Row {
        Text(text = "${contact.name} ${contact.phone}")
    }
}