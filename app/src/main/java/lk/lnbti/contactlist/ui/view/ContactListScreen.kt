package lk.lnbti.contactlist.ui.view

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.view_model.ContactListViewModel

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
                ContactList(onContactItemClicked=onContactItemClicked, modifier = modifier)
            }
        }
    }
}

@Composable
fun AddNewContactButton(onNewContactClicked: () -> Unit) {
    val context = LocalContext.current
    FloatingActionButton(
        shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
        contentColor = Color.White,
        onClick = onNewContactClicked
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
    }
}

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
        contacts?.let {
            items(contacts) {
                ListItem(item = it, onContactItemClicked = onContactItemClicked)
            }
        }
    }
}

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
            Text(text = item.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )

        }

    }
}