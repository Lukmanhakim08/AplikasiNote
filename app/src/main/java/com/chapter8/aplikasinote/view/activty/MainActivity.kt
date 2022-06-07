package com.chapter8.aplikasinote.view.activty

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chapter8.aplikasinote.view.adapter.AdapterNote
import com.chapter8.aplikasinote.R
import com.chapter8.aplikasinote.roomdatabase.NoteDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var dbNote : NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = this.getSharedPreferences("DATAUSER", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("USERNAME", "")
        text_username.text = "Welcome, $username"

        dbNote = NoteDatabase.getInstance(this)
        GetDataNote()
        btn_add.setOnClickListener {
            val pindah = Intent(this, TambahNoteActivity::class.java)
            startActivity(pindah)
        }
    }

    fun GetDataNote(){
        rv_student.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dbNote = NoteDatabase.getInstance(this)
        GlobalScope.launch {
            val listNote = dbNote?.noteDao()?.getAllNote()
            runOnUiThread {
                listNote.let {
                    val adapt = AdapterNote(it!!)
                    rv_student.adapter = adapt
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        GetDataNote()
    }

    override fun onDestroy() {
        super.onDestroy()
        NoteDatabase.destroyInstace()
    }
}