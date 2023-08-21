package lk.lnbti.contactlist.ui.view
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData
import lk.lnbti.contactlist.view_model.ContactListViewModel

@Composable
fun ContactListScreen(onContactItemClicked: (String) -> Unit={},modifier: Modifier = Modifier){
    Box(modifier = Modifier){
        Column {
            ContactList(onContactItemClicked)
        }
    }
}

@Composable
fun ContactList(
    onContactItemClicked: (String) -> Unit,
    listState: LazyListState = rememberLazyListState(),
    contactListViewModel: ContactListViewModel= viewModel()
){
    val contactListUiState by contactListViewModel.uiState.collectAsState()
    var contacts =contactListUiState.contactList
    LazyColumn(
        state = listState
    ){
        contacts?.let {
            items(contacts){
                ListItem(item = it,onContactItemClicked=onContactItemClicked)
            }
        }
    }
}

@Composable
fun ListItem(
    item:Contact,
    onContactItemClicked: (String) -> Unit
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onContactItemClicked(item.name) }
    ){
        Text(text = item.name)
    }
}