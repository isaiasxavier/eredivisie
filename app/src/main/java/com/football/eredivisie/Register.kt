package com.football.eredivisie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonRegister: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password_toggle)
        buttonRegister = findViewById(R.id.btn_register)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.loginNow)
        textView.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        buttonRegister.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and Password must not be empty", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        progressBar.visibility = View.GONE
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Authentication failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}