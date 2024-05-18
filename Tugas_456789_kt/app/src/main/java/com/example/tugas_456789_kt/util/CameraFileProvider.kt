package com.example.tugas_456789_kt.util

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.tugas_456789_kt.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects

class CameraFileProvider: FileProvider(
    R.xml.path_provider
) {
    companion object{
        fun getContentUri(context: Context): Uri{
            val currentTime = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US).format(Date())
            val imageFile = File(context.filesDir, currentTime)
            val authority = context.packageName + ".provider"

            return getUriForFile(
                Objects.requireNonNull(context),
                authority,
                imageFile
            )
        }
    }
}