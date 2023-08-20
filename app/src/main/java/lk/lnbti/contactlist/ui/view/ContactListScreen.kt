package lk.lnbti.contactlist.ui.view
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContactListScreen(modifier: Modifier = Modifier){
    Box(modifier = Modifier){
        Column {
            ContactList()
        }
    }
}

@Composable
fun ContactList(){
    Text(text = "Contact 2")
}