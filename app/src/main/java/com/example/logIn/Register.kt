package com.example.logIn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.EditText
import android.widget.Toast


class Register : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etDeliveryAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        title = "Register Yourself"

        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etDeliveryAddress = findViewById(R.id.etDeliveryAddress)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)

    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun welcomeAc(view: View) {

        etName.background = resources.getDrawable(R.drawable.edit_text_border)
        etPhone.background = resources.getDrawable(R.drawable.edit_text_border)
        etEmail.background = resources.getDrawable(R.drawable.edit_text_border)
        etDeliveryAddress.background = resources.getDrawable(R.drawable.edit_text_border)
        etPassword.background = resources.getDrawable(R.drawable.edit_text_border)
        etConfirmPassword.background = resources.getDrawable(R.drawable.edit_text_border)

        if (etEmail.text.toString() == sharedPreferences.getString(
                "Email",
                "123"
            ) && etEmail.text.toString() != ""
        ) {
            Toast.makeText(this, "Email Already Registered", Toast.LENGTH_LONG).show()
            etEmail.background = resources.getDrawable(R.drawable.error_edittext)
        } else if (etPhone.text.toString() == sharedPreferences.getString(
                "Phone",
                "abc"
            ) && etPhone.text.toString() != ""
        ) {
            Toast.makeText(this, "Mobile Number Already Registered", Toast.LENGTH_LONG).show()
            etPhone.background = resources.getDrawable(R.drawable.error_edittext)
        } else if (etPassword.text.toString() != etConfirmPassword.text.toString()) {
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_LONG).show()
            etPassword.background = resources.getDrawable(R.drawable.error_edittext)
            etConfirmPassword.background = resources.getDrawable(R.drawable.error_edittext)
        } else {
            val name = etName.text.toString()
            val mail = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val address = etDeliveryAddress.text.toString()
            val pass = etPassword.text.toString()
            val cPass = etConfirmPassword.text.toString()
            when {
                name.trim() == "" -> {
                    Toast.makeText(this, "Enter Your Name!", Toast.LENGTH_LONG).show()
                    etName.background = resources.getDrawable(R.drawable.error_edittext)
                }

                mail.trim() == "" -> {
                    Toast.makeText(this, "Enter Your Email!", Toast.LENGTH_LONG)
                        .show()
                    etEmail.background = resources.getDrawable(R.drawable.error_edittext)
                }
                !isEmailValid(mail) -> {
                    Toast.makeText(
                        this, "Enter Valid Email",
                        Toast.LENGTH_LONG
                    ).show()
                    etEmail.background = resources.getDrawable(R.drawable.error_edittext)
                }

                phone.trim() == "" -> {
                    Toast.makeText(
                        this, "Enter Your Mobile Number!",
                        Toast.LENGTH_LONG
                    ).show()
                    etPhone.background = resources.getDrawable(R.drawable.error_edittext)
                }

                phone.length != 10 -> {
                    Toast.makeText(
                        this, "Mobile Number must have 10 digits",
                        Toast.LENGTH_LONG
                    ).show()
                    etPhone.background = resources.getDrawable(R.drawable.error_edittext)
                }

                address.trim() == "" -> {
                    Toast.makeText(
                        this, "Enter Your Address!",
                        Toast.LENGTH_LONG
                    ).show()
                    etDeliveryAddress.background = resources.getDrawable(R.drawable.error_edittext)
                }

                pass.trim() == "" -> {
                    Toast.makeText(this, "Enter Password!", Toast.LENGTH_LONG)
                        .show()
                    etPassword.background = resources.getDrawable(R.drawable.error_edittext)
                }

                cPass.trim() == "" -> {
                    Toast.makeText(
                        this, "Enter Confirm Password!",
                        Toast.LENGTH_LONG
                    ).show()
                    etConfirmPassword.background = resources.getDrawable(R.drawable.error_edittext)
                }

                else -> {
                    sharedPreferences.edit().putString("Name", etName.text.toString()).apply()
                    sharedPreferences.edit().putString("Email", etEmail.text.toString()).apply()
                    sharedPreferences.edit().putString("Phone", etPhone.text.toString()).apply()
                    sharedPreferences.edit()
                        .putString("DeliveryAddress", etDeliveryAddress.text.toString()).apply()
                    sharedPreferences.edit().putString("Password", etPassword.text.toString())
                        .apply()

                    val intent = Intent(this, Welcome::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }


}
