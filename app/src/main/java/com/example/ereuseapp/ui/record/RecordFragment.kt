package com.example.ereuseapp.ui.record

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ereuseapp.MainActivity
import com.example.ereuseapp.R
import com.example.ereuseapp.RecordManagement
import com.example.ereuseapp.dialogs.DeleteRecordDialog
import com.example.ereuseapp.models.DevicePreview
import com.example.ereuseapp.ui.device.DeviceFragment

class RecordFragment(context: Context) : Fragment() {
    private lateinit var recordEmptyView: View
    private lateinit var activityMain: MainActivity
    private val adapter = DevicePreviewAdapter(context, this::onPreviewClick, this::onLongClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_record, container, false)
        activityMain = (activity as MainActivity)

        val recyclerView: RecyclerView = view.findViewById(R.id.deviceRecyclerView)
        recordEmptyView = view.findViewById(R.id.emptyRecord)

        recyclerView.layoutManager = LinearLayoutManager(activityMain)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(activityMain, DividerItemDecoration.VERTICAL)
        )

        adapter.setData(RecordManagement.getDevicesPreview())
        updateRecordList()

        return view
    }

    private fun onPreviewClick(preview: DevicePreview) {
        activityMain.supportFragmentManager.beginTransaction().replace(R.id.mainFragment, DeviceFragment.newInstance(preview)).commit()
    }

    private fun onLongClick(id: String) {
        DeleteRecordDialog.create(requireContext(), { RecordManagement.deleteDevicePreview(id) },
            getString(R.string.warning_dialog), getString(R.string.delete_device_dialog_msg)).show()
    }

    private fun updateRecordList() {
        if (adapter.itemCount == 0) recordEmptyView.visibility = View.VISIBLE
        else recordEmptyView.visibility = View.INVISIBLE
    }
}