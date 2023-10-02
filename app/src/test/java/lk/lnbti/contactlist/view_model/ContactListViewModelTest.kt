package lk.lnbti.contactlist.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import lk.lnbti.contactlist.data.Contact
import lk.lnbti.contactlist.service.ContactService
import lk.lnbti.contactlist.ui_state.ContactListUiState
import org.junit.After
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
class ContactListViewModelTest {

    // Rule to make LiveData work with JUnit
    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ContactListViewModel

    @Mock
    private lateinit var contactService: ContactService

    // Mock LiveData for contactList
    @Mock
    lateinit var contactListObserver: Observer<List<Contact>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ContactListViewModel(contactService)
        viewModel.contactList.observeForever(contactListObserver)
    }

    @After
    fun tearDown() {
        viewModel.contactList.removeObserver(contactListObserver)
    }

    @Test
    fun `should initialize ViewModel and load contact list`() {
        // Verify that the ViewModel is initialized and the initial contact list is loaded
        viewModel.loadContactList()
        Assert.assertTrue(viewModel.contactList.isInitialized)
    }

    @Test
    fun `should search contacts and update searchQuery`() {
        val newContacts = listOf(
            Contact("Sara", "1234567890"),
            Contact("Aruni", "0123456789"),
        )
        val searchedContacts = listOf(
            Contact("Sara", "1234567890"),
        )

        val query = "Sara"

        // When searchContacts is called
        Mockito.`when`(contactService.searchContacts(query = query))
            .then { ContactListUiState.loadLectureList(searchedContacts) }

        viewModel.searchContacts(query)

        // Verify that searchQuery is updated
        Assert.assertEquals(query, viewModel.searchQuery)

        // Verify that contactList is updated with the searched contacts
        Assert.assertEquals(searchedContacts, viewModel.contactList.value)
    }

    @Test
    fun `should update contactList when contacts are loaded`() {
        val contacts = listOf(
            Contact("Sara", "1234567890"),
            Contact("Aruni", "0123456789"),
        )

        ContactListUiState.loadLectureList(contacts)

        // Verify that contactList is updated with the loaded contacts
        Assert.assertEquals(contacts, viewModel.contactList.value)
    }
}