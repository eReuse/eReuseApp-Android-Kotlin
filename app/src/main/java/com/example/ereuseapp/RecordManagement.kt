package com.example.ereuseapp

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.ereuseapp.models.DevicePreview
import com.example.ereuseapp.models.DevicePreviewColumns

class RecordManagement {
        companion object {
            lateinit var context: Context
            lateinit var dbHelper: RecordSQLHelper

            fun provideContext(ctx: Context) {
                context = ctx
                dbHelper = RecordSQLHelper(context)
            }

            fun setDevicePreview(id: String, date: String, image: String, title: String, type: String, supplier: String, score: Int, reparability: Float, processor: Int, ram: Int): Long {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put(DevicePreviewColumns.COLUMN_PRODUCTID, id)
                    put(DevicePreviewColumns.COLUMN_DATE, date)
                    put(DevicePreviewColumns.COLUMN_IMAGE, image)
                    put(DevicePreviewColumns.COLUMN_TITLE, title)
                    put(DevicePreviewColumns.COLUMN_TYPE, type)
                    put(DevicePreviewColumns.COLUMN_SUPPLIER, supplier)
                    put(DevicePreviewColumns.COLUMN_SCORE, score)
                    put(DevicePreviewColumns.COLUMN_REPARABILITY, reparability)
                    put(DevicePreviewColumns.COLUMN_PROCESSOR, processor)
                    put(DevicePreviewColumns.COLUMN_RAM, ram)
                }
                return db?.insert(DevicePreviewColumns.TABLE_NAME, null, values)!!
            }

            fun deleteDevicePreview(id: String) {
                val db = dbHelper.writableDatabase
                db?.delete(DevicePreviewColumns.TABLE_NAME, "PRODUCT_ID = ?", arrayOf(id))
            }

            fun getDevicesPreview(): MutableList<DevicePreview> {
                val readDb = dbHelper.readableDatabase
                val projection = arrayOf(BaseColumns._ID, DevicePreviewColumns.COLUMN_PRODUCTID, DevicePreviewColumns.COLUMN_DATE, DevicePreviewColumns.COLUMN_IMAGE, DevicePreviewColumns.COLUMN_TITLE, DevicePreviewColumns.COLUMN_TYPE, DevicePreviewColumns.COLUMN_SUPPLIER,
                    DevicePreviewColumns.COLUMN_SCORE, DevicePreviewColumns.COLUMN_REPARABILITY, DevicePreviewColumns.COLUMN_PROCESSOR, DevicePreviewColumns.COLUMN_RAM)

                val cursor = readDb.query(
                    DevicePreviewColumns.TABLE_NAME, projection, null, null, null,
                    null, BaseColumns._ID + " DESC"
                )

                val dataset = mutableListOf<DevicePreview>()
                with(cursor) {
                    while (moveToNext()) {
                        val id = getString(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_PRODUCTID))
                        val date = getString(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_DATE))
                        val image = getString(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_IMAGE))
                        val title = getString(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_TITLE))
                        val type = getString(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_TYPE))
                        val supplier = getString(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_SUPPLIER))
                        val score = getInt(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_SCORE))
                        val reparability = getFloat(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_REPARABILITY))
                        val processor = getInt(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_PROCESSOR))
                        val ram = getInt(getColumnIndexOrThrow(DevicePreviewColumns.COLUMN_RAM))
                        dataset.add(DevicePreview(id, date, image, title, type, supplier, score, reparability, processor, ram))
                    }
                }
                return dataset
            }
        }
}