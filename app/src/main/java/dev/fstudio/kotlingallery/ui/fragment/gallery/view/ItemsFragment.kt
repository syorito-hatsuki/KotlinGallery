package dev.fstudio.kotlingallery.ui.fragment.gallery.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.moxun.tagcloudlib.view.TagCloudView
import dev.fstudio.kotlingallery.R
import dev.fstudio.kotlingallery.databinding.FragmentItemsBinding
import dev.fstudio.kotlingallery.util.GalleryHelper


class ItemsFragment : Fragment() {

    private lateinit var tagCloudView: TagCloudView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentItemsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_items, container, false)

        tagCloudView = binding.cloudView

        setHasOptionsMenu(true)

        val permission =
            ContextCompat.checkSelfPermission(
                context as Activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                200
            )
        } else {
            GalleryHelper.loadImages(activity as Activity, tagCloudView)
            (activity as AppCompatActivity?)?.supportActionBar?.subtitle =
                "Image count on device is ${GalleryHelper.getImageCount( requireContext())}"
        }


        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 200) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.i("", "Permission has been denied by user")
            } else {
                activity?.finish()
                startActivity(activity?.intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.menuToolbarRefresh -> {
                GalleryHelper.loadImages(context as Activity, tagCloudView)
                (activity as AppCompatActivity).supportActionBar?.subtitle =
                    "Image count on device is ${GalleryHelper.getImageCount(requireContext())}"
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}