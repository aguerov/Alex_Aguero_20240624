package com.microdesarrollo.alex_aguero_20240624


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.btnLogin)
        loginButton.setOnClickListener {
            val username: EditText = findViewById(R.id.etUsername)
            val password: EditText = findViewById(R.id.etPassword)

            val sharedPreferences: SharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username.text.toString())
            editor.putBoolean("isLoggedIn", true)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username", username.text.toString())
            startActivity(intent)
            finish()
        }
    }
}
