package rs.raf.vezbe11.presentation.view.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.view.fragments.LoginFragment
import rs.raf.vezbe11.presentation.view.fragments.SignUpFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginFragment = LoginFragment()
        val signupFragment = SignUpFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment, loginFragment)
                .addToBackStack(null)
                .commit()

        }
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnSignup: Button = findViewById(R.id.btnSignup)

        btnLogin.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, signupFragment)
                addToBackStack(null)
                commit()
            }
        }

        btnSignup.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, signupFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}