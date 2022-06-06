package com.chapter8.aplikasinote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class AdapterNote(val listDataNote : List<Note>) : RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    private var adb : NoteDatabase? = null
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text_id.text = listDataNote[position].id.toString()
        holder.itemView.text_nama.text = listDataNote[position].nama
        holder.itemView.text_kegiatan.text = listDataNote[position].kegiatan

    }

    override fun getItemCount(): Int {
        return listDataNote.size
    }
}