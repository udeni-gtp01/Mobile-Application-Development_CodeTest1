package lk.lnbti.contactlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import lk.lnbti.contactlist.dao.ContactDao
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.data.ContactData
import java.util.concurrent.Executors

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var instance: ContactDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getInstance(context: Context): ContactDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, ContactDatabase::class.java, "contact_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
                    .apply {
                        // Insert initial data here
                        populateDatabase(this)
                    }
                    .also { instance = it }
            }
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: ContactDatabase) {
            databaseWriteExecutor.execute(
                Runnable {
                    // Populate the database in the background.
                    val contactDao: ContactDao = instance!!.contactDao()
                    ContactData.contacts.forEach { contactDao.insert(it) }
                })
        }
    }
}