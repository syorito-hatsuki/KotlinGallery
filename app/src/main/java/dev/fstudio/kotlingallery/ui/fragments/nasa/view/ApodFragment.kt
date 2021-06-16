package dev.fstudio.kotlingallery.ui.fragments.nasa.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.moxun.tagcloudlib.view.TagCloudView
import dev.fstudio.kotlingallery.R
import dev.fstudio.kotlingallery.databinding.FragmentItemsBinding
import dev.fstudio.kotlingallery.ui.fragments.nasa.adapter.ApodAdapter
import dev.fstudio.kotlingallery.ui.fragments.nasa.impl.ApodAPI
import dev.fstudio.kotlingallery.ui.fragments.nasa.model.ApodItem
import org.koin.android.ext.android.inject


class ApodFragment : Fragment() {

    private val service by inject<ApodAPI>()
    lateinit var tagCloudView: TagCloudView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentItemsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_items, container, false)

        (activity as AppCompatActivity).supportActionBar?.subtitle = ""

        setHasOptionsMenu(true)

        tagCloudView = binding.cloudView

        Toast.makeText(context, "", Toast.LENGTH_SHORT).show()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            kotlin.runCatching {
                service.getApods(100)
            }.onSuccess {
                val list = mutableListOf<ApodItem>()

                it.forEach { item ->
                    item.url?.let { url ->
                        item.date?.let { date ->
                            if (url.isNotEmpty() && date.isNotEmpty()) {
                                list += item
                            }
                        }
                    }
                }

                tagCloudView.setAdapter(ApodAdapter(list))
            }.onFailure {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
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

                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }
}