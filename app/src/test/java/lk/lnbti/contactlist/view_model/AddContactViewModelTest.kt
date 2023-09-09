package lk.lnbti.contactlist.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AddContactViewModelTest{
    @get:Rule
    val instantTaskExecutorRule : InstantTaskExecutorRule=InstantTaskExecutorRule()

    private lateinit var viewModel: AddContactViewModel

    @Before
    fun setup() {
        viewModel = AddContactViewModel()
    }

    @Test
    fun testContactNameValidation() {
        // Initially, the name should be invalid
        Assert.assertFalse(viewModel.isValidContactName)

        // Valid name
        viewModel.updateContactName("Anil Perera")
        Assert.assertTrue(viewModel.isValidContactName)

        // Empty name
        viewModel.updateContactName("")
        Assert.assertFalse(viewModel.isValidContactName)
    }

    @Test
    fun testContactPhoneValidation() {
        // Initially, the phone should be invalid
        Assert.assertFalse(viewModel.isValidPhone)

        // Valid phone number
        viewModel.updateContactPhone("1234567890")
        Assert.assertTrue(viewModel.isValidPhone)

        // Empty phone number
        viewModel.updateContactPhone("")
        Assert.assertFalse(viewModel.isValidPhone)

        // Phone number with non-digit characters
        viewModel.updateContactPhone("hello")
        Assert.assertFalse(viewModel.isValidPhone)

        // Phone number with incorrect length
        viewModel.updateContactPhone("12345")
        Assert.assertFalse(viewModel.isValidPhone)
    }

    @Test
    fun testSaveNewContact() {
        // Valid contact data
        viewModel.updateContactName("Anil Perera")
        viewModel.updateContactPhone("1234567890")

        val savedContactName = viewModel.saveNewContact()

        // Check if the contact name matches the saved name
        Assert.assertEquals("Anil Perera", savedContactName)

        // After saving, both fields should be reset
        Assert.assertTrue(viewModel.newContactName.isBlank())
        Assert.assertTrue(viewModel.newContactPhone.isBlank())
    }

    @Test
    fun testResetNewContact() {
        viewModel.updateContactName("Anil Perera")
        viewModel.updateContactPhone("1234567890")

        viewModel.resetNewContact()

        Assert.assertTrue(viewModel.newContactName.isBlank())
        Assert.assertTrue(viewModel.newContactPhone.isBlank())
    }
}