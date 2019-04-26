package com.iriasan.gotbets.core.kompressor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.net.Uri
import com.bumptech.glide.Glide
import com.iriasan.gotbets.AndroidApplication
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.doAsync
import java.io.File
import java.io.IOException


class Kompressor(context: Context) {
    // max width and height values of the compressed profilePicture is taken as 612x816
    private var maxWidth = 500
    private var maxHeight = 500
    private var compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG
    private var quality = 80
    private var destinationDirectoryPath: String? = null

    init {
        destinationDirectoryPath = context.cacheDir.path + File.separator + "images"
    }

    fun setMaxWidth(maxWidth: Int): Kompressor {
        this.maxWidth = maxWidth
        return this
    }

    fun setMaxHeight(maxHeight: Int): Kompressor {
        this.maxHeight = maxHeight
        return this
    }

    fun setCompressFormat(compressFormat: Bitmap.CompressFormat): Kompressor {
        this.compressFormat = compressFormat
        return this
    }

    fun setQuality(quality: Int): Kompressor {
        this.quality = quality
        return this
    }

    fun setDestinationDirectoryPath(destinationDirectoryPath: String): Kompressor {
        this.destinationDirectoryPath = destinationDirectoryPath
        return this
    }

    fun scaleBitmap(bitmap: Bitmap, wantedWidth: Int, wantedHeight: Int): Bitmap {
        val output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val m = Matrix()
        m.setScale(wantedWidth.toFloat() / bitmap.width, wantedHeight.toFloat() / bitmap.height)
        canvas.drawBitmap(bitmap, m, Paint())

        return output
    }

    @Throws(IOException::class)
    fun compressToBase64(uriPath: Uri?, callback: (String) -> Unit) {
        doAsync {
            Glide.with(AndroidApplication.instance.getContext()!!).asBitmap().load(uriPath).submit()
                    .get()?.let {
                        ImageUtil.encodeToBase64(it, compressFormat, 500)
                    }.toString().doAsync {
                        this.weakRef.get()?.let { it -> callback(it) }
                    }
        }

    }

    @Throws(IOException::class)
    fun compressImagesToBase64(uriPaths: List<Uri?>, callback: (List<String?>) -> Unit) {
        if (uriPaths.isNullOrEmpty()) {
            callback(emptyList())
        } else {
            val acc: MutableList<String?> = mutableListOf()
            uriPaths.forEach { uri ->
                runBlocking {
                    kotlin.run {
                        compressToBase64(uri) {
                            acc.add(it)
                            if (acc.count() == uriPaths.count()) {
                                callback(acc.toList())
                            }
                        }
                    }
                }
            }
        }
    }
}









