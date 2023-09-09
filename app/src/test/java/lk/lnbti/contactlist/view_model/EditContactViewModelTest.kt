package lk.lnbti.contactlist.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class EditContactViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contactService: ContactService

    @Mock
    private lateinit var contactObserver: Observer<Contact>

    private lateinit var viewModel: EditContactViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = EditContactViewModel(contactService)
    }


    @Test
    fun `test search for existing contact`() {
        val contactName = "Anil Perera"
        val contact = Contact(contactName, "1234567890")

        // Mock the behavior of the ContactService to return the contact
        Mockito.`when`(contactService.getContact(contactName)).thenReturn(contact)

        viewModel.searchContact(contactName)

        Assert.assertEquals(contactName, viewModel.updatedContactName)
        Assert.assertEquals(contact.phone, viewModel.updatedContactPhone)
    }

    @Test
    fun `test save edited contact`() {

        val originalContactName = "Anil Perera"
        val originalContactPhone = "Anil Perera"
        val updatedContactName = "Updated Name"
        val updatedContactPhone = "9876543210"

        // Mock the behavior of the ContactService to return the original contact
        Mockito.`when`(contactService.getContact(originalContactName)).thenReturn(
            Contact(originalContactName, originalContactPhone)
        )

        viewModel.searchContact(originalContactName)
        viewModel.updateContactName(updatedContactName)
        viewModel.updateContactPhone(updatedContactPhone)
        val savedContactName = viewModel.saveContact()

        Assert.assertEquals(updatedContactName, savedContactName)
    }
}