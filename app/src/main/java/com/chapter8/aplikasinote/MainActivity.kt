package com.chapter8.aplikasinote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var nDbnew : NoteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nDbnew = NoteDatabase.getInstance(this)
        btn_add.setOnClickListener {
            val pindah = Intent(this, TambahNoteActivity::class.java)
            startActivity(pindah)
        }
        GetDataNote()
    }

    fun GetDataNote(){
        rv_student.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch {
            val listData = nDbnew?.noteDao()?.getAllNote()
            runOnUiThread {
                listData.let {
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