package dev.fstudio.kotlingallery.utils

import android.content.Context
import android.provider.MediaStore
import dev.fstudio.kotlingallery.ui.activities.main.model.Item

object GalleryHelper {

    fun getAllImages(context: Context): ArrayList<Item> {

        val items = arrayListOf<Item>()

        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.MediaColumns.DATA),
            null,
            arrayOf<String>(),
            null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val name = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                )
                items += Item(name.substring(name.lastIndexOf("/") + 1, name.length), name)
            }
        }

        return items
    }
}