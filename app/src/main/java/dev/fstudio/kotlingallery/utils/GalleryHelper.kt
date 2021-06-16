package dev.fstudio.kotlingallery.utils

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.provider.MediaStore
import com.moxun.tagcloudlib.view.TagCloudView
import dev.fstudio.kotlingallery.ui.fragments.gallery.adapter.GalleryAdapter
import dev.fstudio.kotlingallery.ui.fragments.gallery.model.GalleryItem
import kotlin.random.Random

object GalleryHelper {

    private fun getAllImages(context: Context): ArrayList<GalleryItem> {

        val items = arrayListOf<GalleryItem>()

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
                items += GalleryItem(name.substring(name.lastIndexOf("/") + 1, name.length), name)
            }
        }

        return items
    }

    fun getImageCount(context: Context): Int {
        return getAllImages(context).size
    }

    fun loadImages(activity: Activity, tagCloudView: TagCloudView) {
        var list = arrayListOf<GalleryItem>()
        list.clear()
        list = getAllImages(activity)
        if (list.size > 100){
            repeat((1..list.size - 100).count()) {
                list.removeAt(Random(SystemClock.elapsedRealtime()).nextInt(0, list.size))
            }
        }
        tagCloudView.setAdapter(GalleryAdapter(list))
    }

}