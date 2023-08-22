package lk.lnbti.contactlist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import lk.lnbti.contactlist.R
import lk.lnbti.contactlist.view_model.ContactInfoViewModel

@Composable
fun ContactInfoScreen(
    contactName: String?,
    contactInfoViewModel: ContactInfoViewModel = viewModel(),
    onCancelButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    onEditButtonClicked: (String) -> Unit,
) {
    contactInfoViewModel.searchContact(contactName)
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
                    contactInfoViewModel.contact?.name?.let {
                        Text(
                            modifier = Modifier.fillParentMaxWidth(),
                            text = it,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    contactInfoViewModel.contact?.phone?.let {
                        Text(
                            modifier = Modifier.fillParentMaxWidth(),
                            text = it,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { contactInfoViewModel.contact?.let { onEditButtonClicked(it.name) } }
            ) {
                Text(
                    text = stringResource(R.string.edit),
                    fontSize = 16.sp
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    contactInfoViewModel.deleteContact()
                    onDeleteButtonClicked()
                }
            ) {
                Text(
                    text = stringResource(R.string.delete),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                onClick = {
                    onCancelButtonClicked()
                },
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

//@Composable
//fun findContact(
//    //contactViewModel: ContactViewModel,
//    contactViewModel: ContactViewModel = viewModel(),
//    contactName: String?,
//    onContactInfoCancel: () -> Unit
//) {
//    if (contactViewModel.isContactEditable) {
//        EditContactSection(contactViewModel = contactViewModel)
//    } else {
//        ViewContactSection(contactViewModel = contactViewModel, contactName = contactName, onContactInfoCancel = onContactInfoCancel)
//    }
//}


