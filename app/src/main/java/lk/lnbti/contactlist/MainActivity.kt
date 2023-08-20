package lk.lnbti.contactlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lk.lnbti.contactlist.ui.theme.ContactListTheme
import lk.lnbti.contactlist.ui.view.ContactListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactListApp()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactListApp() {
    ContactListTheme {
        var currentScreen: AppDestination by remember { mutableStateOf(ContactList) }
        val navController= rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //currentScreen.screen()
            NavHost(navController = navController, startDestination = ContactList.route){
                composable(route = ContactList.route){
                    ContactList.screen
                }

                composable(route = ContactInfo.route){
                    ContactInfo.screen
                }
            }
        }

    }
}