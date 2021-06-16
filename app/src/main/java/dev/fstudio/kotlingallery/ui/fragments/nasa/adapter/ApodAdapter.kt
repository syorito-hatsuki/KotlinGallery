package dev.fstudio.kotlingallery.ui.fragments.nasa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.moxun.tagcloudlib.view.TagsAdapter
import dev.fstudio.kotlingallery.R
import dev.fstudio.kotlingallery.databinding.ItemApodBinding
import dev.fstudio.kotlingallery.ui.fragments.nasa.model.ApodItem

class ApodAdapter(private val list: MutableList<ApodItem>) :
    TagsAdapter() {

    override fun getCount(): Int = list.size

    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {

        val binding: ItemApodBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent?.context), R.layout.item_apod, parent, false
        )

        Glide.with(binding.root).load(list[position].url).optionalFitCenter()
            .into(binding.apodImageView)

        return binding.root
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getPopularity(position: Int): Int = position

    override fun onThemeColorChanged(view: View?, themeColor: Int) = Unit
}