package com.cockandroid.dating.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.cockandroid.dating.MainActivity
import com.cockandroid.dating.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val Loginbtn = findViewById<Button>(R.id.Loginbtn)
        Loginbtn.setOnClickListener {

            val ID = findViewById<TextInputEditText>(R.id.IDarea)
            val password = findViewById<TextInputEditText>(R.id.passwordarea)

            auth.signInWithEmailAndPassword(ID.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
                    } else {
                        Toast.makeText(this,"잘못된 아이디 혹은 비밀번호 입니다.",Toast.LENGTH_LONG).show()
                        // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                    }
                }

        }


    }
}