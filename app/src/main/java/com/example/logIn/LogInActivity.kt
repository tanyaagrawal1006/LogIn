package com.example.logIn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LogInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var txtRegister: TextView
    private lateinit var txtForgotPassword: TextView
    private lateinit var btnLogIn: Button
    private lateinit var mobileNumber: String
    lateinit var sharedPreferences: SharedPreferences
    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        txtRegister = findViewById(R.id.txtRegisterHere)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        btnLogIn = findViewById(R.id.btnLogIn)
        mobileNumber = etMobileNumber.text.toString()

        if (isLoggedIn) {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
            finish()
        }


        txtRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        txtForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {

            etMobileNumber.background = resources.getDrawable(R.drawable.edit_text_border)
            etPassword.background = resources.getDrawable(R.drawable.edit_text_border)

            when {
                etMobileNumber.text.toString() != sharedPreferences.getString(
                    "Phone",
                    "0000000000"
                ) -> {
                    Toast.makeText(this, "Phone not Registered", Toast.LENGTH_LONG).show()
                    etMobileNumber.background = resources.getDrawable(R.drawable.error_edittext)

                }
                etPassword.text.toString() != sharedPreferences.getString("Password", "*") -> {
                    Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show()
                    etPassword.background = resources.getDrawable(R.drawable.error_edittext)

                }
                else -> {
                    savePreferences()
                    val intent = Intent(this, Welcome::class.java)
                    intent.putExtra("PhoneNumber", mobileNumber)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun savePreferences() {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
    }

}
