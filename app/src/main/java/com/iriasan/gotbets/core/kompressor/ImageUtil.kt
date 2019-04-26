package com.iriasan.gotbets.core.kompressor

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

internal object ImageUtil {

    private fun scaleImageKeepAspectRatio(bmp: Bitmap, maxSize: Int): Bitmap? {
        val imageWidth = bmp.width
        val imageHeight = bmp.height
        val newHeight = imageHeight * maxSize / imageWidth
        return Bitmap.createScaledBitmap(bmp, maxSize, newHeight, false)
    }

    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat, maxSize: Int): String {
        val baos = ByteArrayOutputStream()
        image.compress(compressFormat,50,baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}