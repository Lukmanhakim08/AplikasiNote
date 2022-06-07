package com.chapter8.aplikasinote.data.dataclass

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) var id : Int?,
    @ColumnInfo(name = "judul") var judul : String,
    @ColumnInfo(name = "waktu") var waktu : String,
    @ColumnInfo(name = "kegiatan")var kegiatan : String
): Parcelable
