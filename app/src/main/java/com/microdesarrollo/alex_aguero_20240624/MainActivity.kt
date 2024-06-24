package com.microdesarrollo.alex_aguero_20240624

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences: SharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User")
        val welcomeTextView: TextView = findViewById(R.id.tvWelcome)
        welcomeTextView.text = "Bienvenido: $username"

        val logoutButton: Button = findViewById(R.id.btnLogout)
        logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val bitcoinButton: Button = findViewById(R.id.btnBitcoinValues)
        bitcoinButton.setOnClickListener {
            startActivity(Intent(this, BitcoinValuesActivity::class.java))
        }
    }
}
