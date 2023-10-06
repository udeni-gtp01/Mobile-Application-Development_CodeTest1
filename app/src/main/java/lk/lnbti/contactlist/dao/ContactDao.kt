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
    suspend fun getAllContacts(): List<Contact>
    @Insert
    fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact_table WHERE name LIKE :name")
    fun filterByName(name: String): List<Contact>

    @Query("SELECT * FROM contact_table WHERE name = :name")
    suspend fun findByName(name: String?): Contact?

    @Query("SELECT * FROM contact_table WHERE id = :id")
    suspend fun findById(id: Int?): Contact?
}