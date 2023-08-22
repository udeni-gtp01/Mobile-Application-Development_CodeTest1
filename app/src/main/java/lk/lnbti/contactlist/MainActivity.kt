package lk.lnbti.contactlist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import lk.lnbti.contactlist.ui.theme.ContactListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactListApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactListApp() {
    ContactListTheme {
        var currentScreen: AppDestination by remember { mutableStateOf(ContactList) }
        val navController = rememberNavController()
        Scaffold(
            content = {
                AppNavHost(
                    navController = navController
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun previewContactListApp() {
    ContactListApp()
}