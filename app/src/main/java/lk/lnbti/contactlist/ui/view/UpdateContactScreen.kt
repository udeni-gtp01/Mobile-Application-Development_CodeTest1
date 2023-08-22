package lk.lnbti.contactlist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import lk.lnbti.contactlist.ContactInfo
import lk.lnbti.contactlist.ContactList
import lk.lnbti.contactlist.R
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.view_model.AddContactViewModel
import lk.lnbti.contactlist.view_model.ContactInfoViewModel
import lk.lnbti.contactlist.view_model.UpdateContactViewModel

@Composable
fun UpdateContactScreen(
    navController: NavHostController,
    contactName:String?,
    onEditButtonClicked:() -> Unit = {},
    onCancelButtonClicked:() -> Unit = {},
) {

    val updateContactViewModel: UpdateContactViewModel = viewModel()
    val contact = remember(contactName) { updateContactViewModel.getContactByName(contactName) }
    if (updateContactViewModel.isUpdateContactDone) {
        // Navigate back to ContactListScreen
        navController.popBackStack(ContactList.route, inclusive = false)
    } else {
        EditContactForm(
            contact = contact,
            updateContactViewModel = updateContactViewModel
        )
    }
}

@Composable
fun EditContactForm(
    contact:Contact,
    updateContactViewModel: UpdateContactViewModel
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyColumn(modifier = Modifier, contentPadding = PaddingValues(16.dp)) {
                item {
                    Spacer(Modifier.height(32.dp))
                    Text(
                        modifier = Modifier.fillParentMaxWidth(),
                        text = "Contact Information",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    TextField(
                        value = updateContactViewModel.updatedContactName,
                        singleLine = true,
                        onValueChange = { updateContactViewModel.updateContactName(it) },
                        label = { Text("Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    TextField(
                        value = updateContactViewModel.updatedContactPhone,
                        singleLine = true,
                        onValueChange = { updateContactViewModel.updateContactPhone(it) },
                        label = { Text("Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { updateContactViewModel.updateContact() }
            ) {
                Text(
                    text = stringResource(R.string.save),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    fontSize = 16.sp
                )
            }
        }
    }
}