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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import lk.lnbti.contactlist.ContactList
import lk.lnbti.contactlist.R
import lk.lnbti.contactlist.view_model.ContactViewModel

@Composable
fun ContactInfoScreen(
    contactName: String?,
    navController: NavHostController,
    contactViewModel: ContactViewModel = viewModel(),
) {
    if (contactViewModel.isContactClosed) {
        navController.popBackStack(ContactList.route, inclusive = false)
    } else {
        findContact(contactName = contactName, contactViewModel = contactViewModel)
    }
}

@Composable
fun findContact(
    contactViewModel: ContactViewModel,
    contactName: String?,
) {
    if (contactViewModel.isContactEditable) {
        EditContactSection(contactViewModel = contactViewModel)
    } else {
        contactViewModel.findContact(contactName = contactName)
        ViewContactSection(contactViewModel = contactViewModel)
    }
}

@Composable
fun ViewContactSection(
    contactViewModel: ContactViewModel,
) {
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
                    Text(
                        modifier = Modifier.fillParentMaxWidth(),
                        text = contactViewModel.newContactName,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.fillParentMaxWidth(),
                        text = contactViewModel.newContactPhone,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { contactViewModel.enableEditContact() }
            ) {
                Text(
                    text = stringResource(R.string.edit),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                onClick = { contactViewModel.resetContact() },
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

@Composable
fun EditContactSection(
    contactViewModel: ContactViewModel
) {
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
                        value = contactViewModel.newContactName,
                        singleLine = true,
                        onValueChange = { contactViewModel.updateContactName(it) },
                        label = { Text("Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    TextField(
                        value = contactViewModel.newContactPhone,
                        singleLine = true,
                        onValueChange = { contactViewModel.updateContactPhone(it) },
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
                onClick = { contactViewModel.updateContact() }
            ) {
                Text(
                    text = stringResource(R.string.save),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                onClick = { contactViewModel.cancelEditContact() },
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