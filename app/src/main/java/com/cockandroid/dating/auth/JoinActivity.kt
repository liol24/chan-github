package com.cockandroid.dating.auth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.cockandroid.dating.MainActivity
import com.cockandroid.dating.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private var uid = ""
    private var table = ""
    private var introduce = ""

//    private val TAG = "JoinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = Firebase.auth

        val newbtn =findViewById<Button>(R.id.newbtn)
        newbtn.setOnClickListener {
            val ID = findViewById<TextInputEditText>(R.id.IDArea)
            val password = findViewById<TextInputEditText>(R.id.passwordArea)

            table = findViewById<TextInputEditText>(R.id.tableArea).text.toString()
            introduce = findViewById<TextInputEditText>(R.id.introduceArea).text.toString()



            auth.createUserWithEmailAndPassword(ID.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Log.d(TAG,user?.uid.toString())
                        uid = user?.uid.toString()

//                        val database = Firebase.database
//                        val myRef = database.getReference("message")
//
//                        myRef.setValue("Hello, World!")
//                        updateUI(user)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(baseContext, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show()
//                        updateUI(null)
                    }
                }
        }
    }
}