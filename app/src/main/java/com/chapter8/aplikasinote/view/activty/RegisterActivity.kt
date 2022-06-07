package com.chapter8.aplikasinote.view.activty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chapter8.aplikasinote.R
import com.chapter8.aplikasinote.data.dataclass.User
import com.chapter8.aplikasinote.roomdatabase.UserDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class RegisterActivity : AppCompatActivity() {
    var dbUser : UserDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbUser = UserDatabase.getInstance(this)
        text_sdhregister.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_register.setOnClickListener {
            GlobalScope.async {
                val nama = edt_namalengkap.text.toString()
                val alamat = edt_alamat.text.toString()
                val username = edt_username.text.toString()
                val password = edt_password.text.toString()
                val konpassw = edt_konpassword.text.toString()

                val simpan = dbUser?.userDao()?.register(User(null, nama, alamat, username, password))
                runOnUiThread {
                    if (nama.isNotEmpty() &&
                        alamat.isNotEmpty() &&
                        username.isNotEmpty() &&
                        password.isNotEmpty() &&
                        konpassw.isNotEmpty()
                    ){
                        if (password == konpassw){
                            if (simpan !=0.toLong()){
                                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                Toast.makeText(this@RegisterActivity, "Register sukse", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(this@RegisterActivity, "Register gagal", Toast.LENGTH_SHORT).show()
                            }
                        } else{
                            Toast.makeText(this@RegisterActivity, "Password dan konfirmasi password harus sama", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@RegisterActivity, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}