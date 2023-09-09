package lk.lnbti.contactlist.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ContactInfoViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: ContactInfoViewModel

    @Mock
    private lateinit var contactService: ContactService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ContactInfoViewModel(contactService)
    }

    @Test
    fun `test search by contact name`() {
        val contactName = "Anil Perera"
        val expectedContact = Contact(contactName, "1234567890")

        Mockito.`when`(contactService.getContact(contactName = contactName))
            .thenReturn(expectedContact)

        viewModel.searchContact(contactName)
        assertEquals(expectedContact, viewModel.contact)
    }

    @Test
    fun `test empty or invalid contact name should return null`() {
        val contactName: String? = null
        val contact = Contact("Anil Perera", "1234567890")

        Mockito.`when`(contactService.getContact(contactName = contactName))
            .thenReturn(contact)

        viewModel.searchContact(contactName)
        assertEquals(null, viewModel.contact)
    }

    @Test
    fun `deleteContact() should delete the currently displayed contact`() {
        val contactName = "Anil Perera"
        val contact = Contact(contactName, "1234567890")
        viewModel.contact = contact

        viewModel.deleteContact()

        assertEquals(null, viewModel.contact)
    }
}
