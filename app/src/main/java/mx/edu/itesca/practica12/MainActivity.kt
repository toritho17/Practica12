package mx.edu.itesca.practica12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import mx.edu.itesca.practica12.databinding.ActivityMainBinding
import mx.edu.itesca.practica12.databinding.ActivitySignInBinding

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// Initialize Firebase Auth
        auth = Firebase.auth


        binding.signInAppCompatButton.setOnClickListener {
            val mEmail=binding.emailEditText.text.toString()
            val mPassword=binding.passwordEditText.text.toString()

            when{
                mEmail.isEmpty() || mPassword.isEmpty()->{
                    Toast.makeText(baseContext, "Mail o Contraseña incorrecta.",
                        Toast.LENGTH_SHORT).show()
                }else ->{
                SignIn(mEmail,mPassword)
                Toast.makeText(baseContext,"BIENVENIDO",Toast.LENGTH_SHORT).show()

            }

            }
        }
    }
    private fun SignIn(email:String, password: String) {
        auth.signInWithEmailAndPassword (email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d( "TAG","signInWithEmail: success")
                    reaload()
                    //val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w( "TAG", "signInWithEmail: failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }


    private fun reaload() {
        val intent= Intent(this,SignInActivity::class.java)
        this.startActivity(intent)
    }
}