package com.chapter8.aplikasinote.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chapter8.aplikasinote.data.dataclass.Note
import com.chapter8.aplikasinote.roomdatabase.NoteDatabase
import com.chapter8.aplikasinote.R
import com.chapter8.aplikasinote.view.activty.MainActivity
import kotlinx.android.synthetic.main.hapus_note.view.*
import kotlinx.android.synthetic.main.item_note.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterNote(
    val listDataNote : List<Note>,
//    val onClick : (Note) -> Unit
) : RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    private var dbNote : NoteDatabase? = null
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text_judul.text = listDataNote[position].judul
        holder.itemView.text_kegiatan.text = listDataNote[position].kegiatan
        holder.itemView.text_waktu.text = listDataNote[position].waktu

        holder.itemView.btn_hapus.setOnClickListener {
            dbNote = NoteDatabase.getInstance(it.context)
            val dialogHapus = LayoutInflater.from(it.context)
                .inflate(R.layout.hapus_note, null, false)
            val hapusDataDialog = AlertDialog.Builder(it.context)
                .setView(dialogHapus)
                .create()
            dialogHapus.btn_cancel.setOnClickListener {
                hapusDataDialog.dismiss()
            }
            dialogHapus.btn_hapus.setOnClickListener {
                GlobalScope.async {
                    val hapus = dbNote?.noteDao()?.hapusNote(listDataNote[position])

                    (dialogHapus.context as MainActivity).runOnUiThread {
                        if (hapus !=0){
                            Toast.makeText(dialogHapus.context, "Note ${listDataNote[position].judul} berhasil dihapus", Toast.LENGTH_SHORT).show()
                            (dialogHapus.context as MainActivity).recreate()
                        }else{
                            Toast.makeText(dialogHapus.context, "Note ${listDataNote[position].judul} gagal dihapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            dialogHapus.showContextMenu()
        }

//        holder.itemView.detail.setOnClickListener {
//            onClick(listDataNote[position])
//        }
    }

    override fun getItemCount(): Int {
        return listDataNote.size
    }
}