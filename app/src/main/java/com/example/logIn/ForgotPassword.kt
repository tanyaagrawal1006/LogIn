package com.example.logIn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class ForgotPassword : AppCompatActivity() {
lateinit var sharedPreferences: SharedPreferences
    lateinit var etEmail: EditText
    lateinit var etPhone: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        title = "Forgot Password"

        etEmail=findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)

    }
    fun welcomeAc(view: View) {
        etPhone.background = resources.getDrawable(R.drawable.edit_text_border)
        etEmail.background = resources.getDrawable(R.drawable.edit_text_border)
        when{
            etPhone.text.toString() != sharedPreferences.getString("Phone","0000000000") ->
            {
                Toast.makeText(this, "Incorrect Mobile Number", Toast.LENGTH_LONG).show()
                etPhone.background = resources.getDrawable(R.drawable.error_edittext)
            }
            etEmail.text.toString() != sharedPreferences.getString("Email","user@gmail.com")->
            {
                Toast.makeText(this, "Incorrect Email", Toast.LENGTH_LONG).show()
                etEmail.background = resources.getDrawable(R.drawable.error_edittext)
            }
            else -> {
                val intent = Intent(this, Welcome::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun savePreferences() {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
    }



}
