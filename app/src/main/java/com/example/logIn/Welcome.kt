package com.example.logIn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Welcome : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var btnLogOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        title = "Welcome"
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtPhone = findViewById<TextView>(R.id.txtPhone1)
        val txtEmail = findViewById<TextView>(R.id.txtEmail1)
        val txtAddress = findViewById<TextView>(R.id.txtAddress1)
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        btnLogOut = findViewById(R.id.btnLogOut)

        var str = sharedPreferences.getString("Name", "User")
        txtName.text = str

        str = sharedPreferences.getString("Phone", "0000000000")
        txtPhone.text = str
        str = sharedPreferences.getString("Email", "user@gmail.com")
        txtEmail.text = str
        str = sharedPreferences.getString("DeliveryAddress", "London, UK")
        txtAddress.text = str

        btnLogOut.setOnClickListener() {
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

    }
}
