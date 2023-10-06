package lk.lnbti.contactlist.data

import android.content.Context
import lk.lnbti.contactlist.database.ContactDatabase
import lk.lnbti.contactlist.service.ContactService
import lk.lnbti.contactlist.service.ContactServiceImpl

interface AppContainer {
    val contactService:ContactService
}

class AppDataContainer(private val context: Context):AppContainer{
    override val contactService: ContactService by lazy {
        ContactServiceImpl(ContactDatabase.getInstance(context).contactDao())
    }

}