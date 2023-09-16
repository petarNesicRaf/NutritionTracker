package rs.raf.vezbe11.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentListBinding
import rs.raf.vezbe11.databinding.FragmentLoginBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.contract.UserContract
import rs.raf.vezbe11.presentation.view.activities.LandingActivity
import rs.raf.vezbe11.presentation.view.activities.MainActivity
import rs.raf.vezbe11.presentation.viewmodel.UserViewModel
import timber.log.Timber


class LoginFragment : Fragment(R.layout.fragment_login) {
    private val userViewModel: UserContract.ViewModel by sharedViewModel<UserViewModel>()

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initObservers()

        _binding = FragmentLoginBinding.bind(view)

        binding.submitButton.setOnClickListener {
            var username = binding.userNameTextField.text.toString()
            var password = binding.passwordTextField.text.toString()

            // Validate the username and password (you can add your validation logic here)

            // Attempt to retrieve the user by username
            var user:Unit
            user = userViewModel.getUsername(username)

            if (username.isEmpty() || password.length < 4) {
                    // Show a toast message for validation errors
                    Toast.makeText(
                        requireContext(),
                        "Please enter a valid username and a password with at least 4 characters",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (user!= null ) {
                    Toast.makeText(
                        requireContext(),
                        "Successfully logged in.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(requireContext(), LandingActivity::class.java )
                    startActivity(intent)

                } else {
                    // Login failed, show an error message to the user
                    Toast.makeText(
                        requireContext(),
                        "Invalid username or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }

    private fun initObservers() {
        userViewModel.user.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




