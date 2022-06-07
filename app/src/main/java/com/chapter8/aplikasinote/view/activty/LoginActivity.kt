package com.chapter8.aplikasinote.view.activty

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chapter8.aplikasinote.R
import com.chapter8.aplikasinote.roomdatabase.UserDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var dbUser : UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        text_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btn_login.setOnClickListener {
            if (AuthUser()){
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun AuthUser(): Boolean{
        if (et_username.text.isNotEmpty() &&
                et_password.text.isNotEmpty()){
            dbUser = UserDatabase.getInstance(this)
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            val login = dbUser?.userDao()?.chekUserDataLogin(username, password)
            return if (login.isNullOrEmpty()){
                Toast.makeText(this, "username dan password salah", Toast.LENGTH_SHORT).show()
                false
            }else{
                val sharedPreferences = this.getSharedPreferences("DATAUSER", Context.MODE_PRIVATE)
                val sf = sharedPreferences.edit()
                sf.putString("USERNAME", login)
                sf.putString("PASSWORD", password)
                sf.apply()
                true
            }
        }else{
            Toast.makeText(this, "username dan password harus diisi", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}