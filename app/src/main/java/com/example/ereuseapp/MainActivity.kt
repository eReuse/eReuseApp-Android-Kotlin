package com.example.ereuseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ereuseapp.dialogs.RepeatedDeviceDialog
import com.example.ereuseapp.models.DevicePreview
import com.example.ereuseapp.ui.record.RecordFragment
import com.example.ereuseapp.ui.scan.ScanFragment
import com.example.ereuseapp.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RecordManagement.provideContext(this)

        val recordFragment = RecordFragment(this)
        val scanFragment = ScanFragment()
        val searchFragment = SearchFragment()

        changeFragment(recordFragment)

        navView = findViewById(R.id.bottomNavigationView)
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.record->changeFragment(recordFragment)
                R.id.scan->changeFragment(scanFragment)
                R.id.search->changeFragment(searchFragment)
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFragment, fragment)
            commit()
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            var ean = result.contents
            if (ean != null) {
                if (ean.length != 13) ean = "0$ean"
                ApiManagement.getDevicePreviewByEAN(ean) { dev: DevicePreview?, s: String? ->
                    if (!s.isNullOrEmpty()) {
                        if (s == "no_ean") runOnUiThread { Toast.makeText(this, getString(R.string.no_matching_ean), Toast.LENGTH_LONG).show() }
                        else runOnUiThread { Toast.makeText(this, s, Toast.LENGTH_LONG).show() }
                    }
                    else {
                        if (dev != null) {
                            var insertResult = 0L
                            insertResult = RecordManagement.setDevicePreview(dev.product_id, dev.release_date, dev.image, dev.title, dev.device_type, dev.supplier, dev.score, dev.reparability, dev.processor, dev.ram)
                            if (insertResult == -1L) {
                                runOnUiThread { RepeatedDeviceDialog.create(this, getString(R.string.repeated_device)).show() }
                            }
                        }
                    }
                    changeFragment(RecordFragment(this))
                    runOnUiThread { navView.selectedItemId = R.id.record }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}