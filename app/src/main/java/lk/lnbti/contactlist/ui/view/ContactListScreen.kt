package lk.lnbti.contactlist.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import lk.lnbti.contactlist.R
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.view_model.ContactListViewModel

/**
 * Composable function for the Contact List screen.
 *
 * @param onContactItemClicked Callback invoked when a contact item is clicked, passing the contact's name.
 * @param onNewContactClicked Callback invoked when the new contact button is clicked.
 * @param modifier Modifier for custom styling.
 */
@Composable
fun ContactListScreen(
    onContactItemClicked: (String) -> Unit,
    onNewContactClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            AddNewContactButton(onNewContactClicked)
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            Column {
                ContactList(onContactItemClicked = onContactItemClicked, modifier = modifier)
            }
        }
    }
}

/**
 * Composable function for the "Add New Contact" floating action button.
 *
 * @param onNewContactClicked Callback invoked when the button is clicked.
 */
@Composable
fun AddNewContactButton(onNewContactClicked: () -> Unit) {
    FloatingActionButton(
        shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
        //contentColor = Color.White,
        onClick = onNewContactClicked
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
    }
}

/**
 * Composable function for the Contact List.
 *
 * @param modifier Modifier for custom styling.
 * @param onContactItemClicked Callback invoked when a contact item is clicked, passing the contact's name.
 * @param listState The [LazyListState] to manage the state of the LazyColumn.
 * @param contactListViewModel The [ContactListViewModel] used to manage the UI state of the contact list.
 */
@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    onContactItemClicked: (String) -> Unit,
    listState: LazyListState = rememberLazyListState(),
    contactListViewModel: ContactListViewModel = viewModel()
) {
    val contactListUiState by contactListViewModel.uiState.collectAsState()
    var contacts = contactListUiState.contactList

    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.navigationBars.asPaddingValues(),
        state = listState
    ) {
        item {
            TextField(
                value = contactListViewModel.searchQuery,
                onValueChange = {
                    contactListViewModel.searchContacts(it)
                },
                label = { Text(stringResource(id = R.string.search)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        contactListViewModel.searchContacts(contactListViewModel.searchQuery)
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
        contacts?.let {
            items(contacts) {
                ListItem(item = it, onContactItemClicked = onContactItemClicked)
            }
        }
    }
}

/**
 * Composable function for a single contact list item.
 *
 * @param item The [Contact] item to display.
 * @param onContactItemClicked Callback invoked when the item is clicked, passing the contact's name.
 */
@Composable
fun ListItem(
    item: Contact,
    onContactItemClicked: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.LightGray)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
            .clickable { onContactItemClicked(item.name) }
    ) {
        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}