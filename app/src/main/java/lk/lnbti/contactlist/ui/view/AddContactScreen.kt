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
import lk.lnbti.contactlist.view_model.AddContactViewModel

/**
 * Composable function for the Add Contact screen.
 *
 * @param addContactViewModel The [AddContactViewModel] used for managing the state and actions of the screen.
 * @param onSaveButtonClicked Callback invoked when the save button is clicked, passing the new contact's name.
 * @param onCancelButtonClicked Callback invoked when the cancel button is clicked.
 */
@Composable
fun AddContactScreen(
    addContactViewModel: AddContactViewModel = viewModel(),
    onSaveButtonClicked: (String) -> Unit,
    onCancelButtonClicked: () -> Unit
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
                        text = stringResource(id = R.string.add_new_contact),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    TextField(
                        value = addContactViewModel.newContactName,
                        singleLine = true,
                        onValueChange = { addContactViewModel.updateContactName(it) },
                        label = {
                            if (addContactViewModel.isValidContactName) {
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
                    Spacer(Modifier.height(16.dp))
                    TextField(
                        value = addContactViewModel.newContactPhone,
                        singleLine = true,
                        onValueChange = { addContactViewModel.updateContactPhone(it) },
                        label = {
                            if (addContactViewModel.isValidPhone) {
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
                    if (addContactViewModel.isValidContactName && addContactViewModel.isValidPhone) {
                        onSaveButtonClicked(addContactViewModel.saveNewContact())
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.submit),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                onClick = {
                    addContactViewModel.resetNewContact()
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