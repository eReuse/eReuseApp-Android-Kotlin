package com.example.ereuseapp.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.ereuseapp.R
import com.example.ereuseapp.TabInteraction
import com.example.ereuseapp.TabsAdapter
import com.example.ereuseapp.models.DevicePreview
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class DeviceFragment : Fragment() {
    private lateinit var devicePreview: DevicePreview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            devicePreview = requireArguments().getParcelable("device")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_device, container, false)

        val imageView: ImageView = view.findViewById(R.id.imageDevice)
        val titleTextView: TextView = view.findViewById(R.id.titleDevice)
        val supplierTextView: TextView = view.findViewById(R.id.supplierDevice)
        val typeTextView: TextView = view.findViewById(R.id.typeDevice)
        val dateTextView: TextView = view.findViewById(R.id.releaseDateDevice)
        val scoreBar: ProgressBar = view.findViewById(R.id.scoreBar)

        Picasso.get().load(devicePreview.image).into(imageView)
        titleTextView.text = devicePreview.title
        if (devicePreview.device_type.equals("Notebooks")) typeTextView.text = context?.getString(R.string.notebooks)
        else typeTextView.text = context?.getString(R.string.desktop)
        scoreBar.progress = devicePreview.score
        dateTextView.text = devicePreview.release_date
        supplierTextView.text = devicePreview.supplier

        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val adapter = TabsAdapter(childFragmentManager, lifecycle)

        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val fragmentItem = childFragmentManager.findFragmentByTag("f$position")
                if (position == 0) {
                        (fragmentItem as TabInteraction).updateFragmentData(devicePreview.product_id)
                }
                else (fragmentItem as TabInteraction).updateFragmentData(devicePreview.reparability.toString())
                super.onPageSelected(position)
            }
        })

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.components)
                1 -> tab.text = getString(R.string.environmental)
            }
        }.attach()

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(device: DevicePreview) =
            DeviceFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("device", device)
                }
            }
    }
}