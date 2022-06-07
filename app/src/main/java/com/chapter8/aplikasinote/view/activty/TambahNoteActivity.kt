package com.chapter8.aplikasinote.view.activty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chapter8.aplikasinote.data.dataclass.Note
import com.chapter8.aplikasinote.roomdatabase.NoteDatabase
import com.chapter8.aplikasinote.R
import kotlinx.android.synthetic.main.activity_tambah_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class TambahNoteActivity : AppCompatActivity() {
    var dbNote : NoteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_note)

//        dbNote = NoteDatabase.getInstance(this)
//        btn_save.setOnClickListener {
//            GlobalScope.async {
//                val nama = edt_nama.text.toString()
//                val kegiatan = edt_kegiatan.text.toString()
//
//                val hasil = dbNote?.noteDao()?.inserNote(Note(null, nama, kegiatan))
//                runOnUiThread {
//                    if (hasil != 0.toLong()){
//                        startActivity(Intent(this@TambahNoteActivity, MainActivity::class.java))
//                        Toast.makeText(this@TambahNoteActivity, "Tambah note sukses", Toast.LENGTH_SHORT).show()
//                    } else{
//                        Toast.makeText(this@TambahNoteActivity, "Tambah data gagal", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
    }
}