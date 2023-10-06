package lk.lnbti.contactlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lk.lnbti.contactlist.dao.ContactDao
import lk.lnbti.contactlist.data.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var instance: ContactDatabase? = null
        fun getInstance(context: Context): ContactDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, ContactDatabase::class.java, "contact_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}