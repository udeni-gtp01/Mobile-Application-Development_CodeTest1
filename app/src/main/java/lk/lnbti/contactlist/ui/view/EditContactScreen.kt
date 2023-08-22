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
import lk.lnbti.contactlist.R
import lk.lnbti.contactlist.view_model.EditContactViewModel

@Composable
fun EditContactScreen(
    editContactViewModel: EditContactViewModel = viewModel(),
    contactName: String?,
    onCancelButtonClicked: (String) -> Unit,
    onSaveButtonClicked: (String) -> Unit
) {
    editContactViewModel.searchContact(contactName)
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
                        text = stringResource(id = R.string.edit_contact),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Spacer(Modifier.height(32.dp))
                    TextField(
                        value = editContactViewModel.updatedContactName,
                        singleLine = true,
                        onValueChange = { editContactViewModel.updateContactName(it) },
                        label = {
                            if (editContactViewModel.isValidContactName) {
                                Text(stringResource(R.string.contact_name))
                            } else {
                                Text(stringResource(R.string.invalid_contact_name))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                item {
                    Spacer(Modifier.height(32.dp))
                    TextField(
                        value = editContactViewModel.updatedContactPhone,
                        singleLine = true,
                        onValueChange = { editContactViewModel.updateContactPhone(it) },
                        label = {
                            if (editContactViewModel.isValidPhone) {
                                Text(stringResource(R.string.contact_phone))
                            } else {
                                Text(stringResource(R.string.invalid_contact_phone))
                            }
                        },
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
                onClick = {
                    if (editContactViewModel.isValidContactName && editContactViewModel.isValidPhone) {
                        onSaveButtonClicked(editContactViewModel.saveContact())
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.save),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                onClick = {
                    onCancelButtonClicked(editContactViewModel.resetContact())
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