package lk.lnbti.contactlist

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import lk.lnbti.contactlist.data.AppContainer
import lk.lnbti.contactlist.data.AppDataContainer

class ContactListApplication:Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
var context:Context=this
    override fun onCreate() {
        super.onCreate()
        Log.d("oyasumi","application started oncreate")
        container = AppDataContainer(this)
    }
    fun CreationExtras.contactListApplication(): ContactListApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ContactListApplication)
}