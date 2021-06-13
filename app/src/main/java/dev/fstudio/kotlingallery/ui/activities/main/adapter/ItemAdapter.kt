package dev.fstudio.kotlingallery.ui.activities.main.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moxun.tagcloudlib.view.TagCloudView
import com.moxun.tagcloudlib.view.TagsAdapter
import dev.fstudio.kotlingallery.R
import dev.fstudio.kotlingallery.databinding.ItemBinding
import dev.fstudio.kotlingallery.ui.activities.main.model.Item

class ItemAdapter(private val list: ArrayList<Item>) :
    TagsAdapter() {

    override fun getCount(): Int = list.size

    override fun getView(context: Context?, position: Int, parent: ViewGroup?): View {

        val binding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent?.context), R.layout.item, parent, false
        )

        Glide.with(binding.root).load(list[position].image).fitCenter()
            .into(binding.itemImageView)

        return binding.root
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getPopularity(position: Int): Int = position

    override fun onThemeColorChanged(view: View?, themeColor: Int) = Unit
}