package lk.lnbti.contactlist.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AddContactViewModelTest {
    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AddContactViewModel

    @Before
    fun setup() {
        viewModel = AddContactViewModel()
    }

    @Test
    fun testContactNameValidation() {
        // Initially, there should not be field errors
        Assert.assertFalse(viewModel.isContactNameError)

        // Valid name
        viewModel.updateContactName("Anil Perera")
        viewModel.validateContactName()
        Assert.assertFalse(viewModel.isContactNameError)

        // Empty name
        viewModel.updateContactName("")
        viewModel.validateContactName()
        Assert.assertTrue(viewModel.isContactNameError)
    }

    @Test
    fun testContactPhoneValidation() {
        // Initially, there should not be field errors
        Assert.assertFalse(viewModel.isPhoneError)

        // Valid phone number
        viewModel.updateContactPhone("1234567890")
        viewModel.validatePhone()
        Assert.assertFalse(viewModel.isPhoneError)

        // Empty phone number
        viewModel.updateContactPhone("")
        viewModel.validatePhone()
        Assert.assertTrue(viewModel.isPhoneError)

        // Phone number with non-digit characters
        viewModel.updateContactPhone("hello")
        viewModel.validatePhone()
        Assert.assertTrue(viewModel.isPhoneError)

        // Phone number with incorrect length
        viewModel.updateContactPhone("12345")
        viewModel.validatePhone()
        Assert.assertTrue(viewModel.isPhoneError)
    }

    @Test
    fun testSaveNewContact() {
        // Valid contact data
        viewModel.updateContactName("Anil Perera")
        viewModel.updateContactPhone("1234567890")

        Assert.assertTrue(viewModel.isValidationSuccessful())
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