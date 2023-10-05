package lk.lnbti.contactlist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import lk.lnbti.contactlist.data.Contact

@Dao
interface ContactDao {
    @Query("select * from contact_table order by name asc")
    fun getAllContacts(): LiveData<List<Contact>>
    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("SELECT * FROM contact_table WHERE name LIKE :name")
    fun filterByName(name: String): List<Contact>

    @Query("SELECT * FROM contact_table WHERE name = :name")
    fun findByName(name: String): Contact
}