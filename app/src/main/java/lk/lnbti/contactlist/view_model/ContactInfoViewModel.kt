package lk.lnbti.contactlist.view_model

import androidx.lifecycle.ViewModel
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData

class ContactInfoViewModel:ViewModel() {
    fun getContactByName(contactName:String?):Contact{
        return ContactData.getContact(contactName)
    }

}