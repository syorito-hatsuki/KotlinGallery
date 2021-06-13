package dev.fstudio.kotlingallery.ui.activities.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.moxun.tagcloudlib.view.TagCloudView
import dev.fstudio.kotlingallery.R
import dev.fstudio.kotlingallery.databinding.ActivityMainBinding
import dev.fstudio.kotlingallery.ui.activities.main.adapter.ItemAdapter
import dev.fstudio.kotlingallery.utils.GalleryHelper
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val tagCloudView: TagCloudView = binding.cloudView

        val list = GalleryHelper.getAllImages(this)
        if (list.size > 100){
            repeat((1..list.size - 100).count()) {
                list.removeAt(Random(SystemClock.elapsedRealtime()).nextInt(0, list.size))
            }
        }

        tagCloudView.setAdapter(ItemAdapter(list))
    }
}