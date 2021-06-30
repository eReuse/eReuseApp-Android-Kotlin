package com.example.ereuseapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ereuseapp.ApiManagement
import com.example.ereuseapp.MainActivity
import com.example.ereuseapp.R
import com.example.ereuseapp.models.DevicePreview
import com.example.ereuseapp.ui.device.DeviceFragment

class SearchFragment(context: Context) : Fragment() {
    private lateinit var recordEmptyView: View
    private lateinit var activityMain: MainActivity
    private val adapter = SearchAdapter(context, this::onPreviewClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        activityMain = (activity as MainActivity)

        val recyclerView: RecyclerView = view.findViewById(R.id.deviceRecyclerView)
        recordEmptyView = view.findViewById(R.id.emptyRecord)

        recyclerView.layoutManager = LinearLayoutManager(activityMain)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(activityMain, DividerItemDecoration.VERTICAL)
        )

        val searchButton: Button = view.findViewById(R.id.searchButton)
        val filter: RadioGroup = view.findViewById(R.id.radioFilter)

        filter.setOnCheckedChangeListener { group, checkedId ->
            searchButton.isEnabled = true
            var type = ""
            if (group.getChildAt(0).id == checkedId) type = "Notebooks"
            else type = "Desktops"

            searchButton.setOnClickListener {
                ApiManagement.getDevicesPreviewByType(type) { devices: List<DevicePreview>, s: String? ->
                    activityMain.runOnUiThread {
                        adapter.setData(devices)
                        updateRecordList()
                    }
                }
            }
        }

        return view
    }

    private fun onPreviewClick(preview: DevicePreview) {
        activityMain.supportFragmentManager.beginTransaction().replace(R.id.mainFragment, DeviceFragment.newInstance(preview)).commit()
    }

    private fun updateRecordList() {
        if (adapter.itemCount == 0) recordEmptyView.visibility = View.VISIBLE
        else recordEmptyView.visibility = View.INVISIBLE
    }
}