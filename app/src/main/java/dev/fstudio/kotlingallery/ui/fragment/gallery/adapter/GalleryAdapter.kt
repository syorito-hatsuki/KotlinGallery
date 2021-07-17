package dev.fstudio.kotlingallery.ui.fragment.gallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.moxun.tagcloudlib.view.TagsAdapter
import dev.fstudio.kotlingallery.R
import dev.fstudio.kotlingallery.databinding.ItemGalleryBinding
import dev.fstudio.kotlingallery.ui.fragment.gallery.model.GalleryItem

class GalleryAdapter(private val list: ArrayList<GalleryItem>) :
    TagsAdapter() {

    override fun getCount(): Int = list.size

    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {

        val binding: ItemGalleryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent?.context), R.layout.item_gallery, parent, false
        )

        Glide.with(binding.root).load(list[position].image).fitCenter()
            .into(binding.itemImageView)

        return binding.root
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getPopularity(position: Int): Int = position

    override fun onThemeColorChanged(view: View?, themeColor: Int) = Unit
}