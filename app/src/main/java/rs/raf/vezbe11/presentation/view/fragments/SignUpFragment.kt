package rs.raf.vezbe11.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.UserEntity
import rs.raf.vezbe11.databinding.FragmentLoginBinding
import rs.raf.vezbe11.databinding.FragmentSignupBinding
import rs.raf.vezbe11.presentation.contract.UserContract
import rs.raf.vezbe11.presentation.view.activities.LandingActivity
import rs.raf.vezbe11.presentation.view.activities.MainActivity
import rs.raf.vezbe11.presentation.viewmodel.UserViewModel
import timber.log.Timber


class SignUpFragment : Fragment(R.layout.fragment_signup) {
    private val userViewModel: UserContract.ViewModel by sharedViewModel<UserViewModel>()
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.bind(view)
        initObservers()
        binding.submitButton.setOnClickListener {
            var username = binding.userNameTextField.text.toString()
            var firstName = binding.firstNameTextField.text.toString()
            var lastName = binding.lastNameTextField.text.toString()
            var password = binding.passwordTextField.text.toString()


            var user = userViewModel.getUsername(username)

            if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.length < 5) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields, the password must be longer than 4 characters.",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (userViewModel.user.value != null && userViewModel.user.value!!.userName == username) {
                Timber.e("Nadjen user " + firstName + " " + lastName + " " + username + " " + password)
                Timber.e("Blabla user " + userViewModel.user.value!!.firstName + " " + userViewModel.user.value!!.lastName + " " + userViewModel.user.value!!.userName + " " + userViewModel.user.value!!.password)

                Toast.makeText(
                    requireContext(),
                    "Please enter a valid username, this username is already in use.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                userViewModel.insert(UserEntity(0, firstName, lastName, username, password))
                Timber.e("Ubaceno " + firstName + " " + lastName + " " + username + " " + password)

                userViewModel.setUserToNull()
                val intent = Intent(this@SignUpFragment.requireContext(), LandingActivity::class.java )
                startActivity(intent)
            }
        }
        }

    private fun initObservers() {
        userViewModel.user.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())

        })

    }
    }

