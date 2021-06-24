package com.example.ereuseapp.models

import android.provider.BaseColumns

object DevicePreviewColumns : BaseColumns {
    const val TABLE_NAME = "records"
    const val COLUMN_PRODUCTID = "product_id"
    const val COLUMN_DATE = "date"
    const val COLUMN_IMAGE = "image"
    const val COLUMN_TITLE = "title"
    const val COLUMN_TYPE = "type"
    const val COLUMN_SUPPLIER = "supplier"
    const val COLUMN_SCORE = "score"
    const val COLUMN_REPARABILITY = "reparability"
    const val COLUMN_PROCESSOR = "processor"
    const val COLUMN_RAM = "ram"
}

const val SQL_CREATE_ENTRIES = "CREATE TABLE ${DevicePreviewColumns.TABLE_NAME} (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "${DevicePreviewColumns.COLUMN_PRODUCTID} TEXT unique," +
        "${DevicePreviewColumns.COLUMN_DATE} TEXT," +
        "${DevicePreviewColumns.COLUMN_IMAGE} TEXT," +
        "${DevicePreviewColumns.COLUMN_TITLE} TEXT," +
        "${DevicePreviewColumns.COLUMN_TYPE} TEXT," +
        "${DevicePreviewColumns.COLUMN_SUPPLIER} TEXT," +
        "${DevicePreviewColumns.COLUMN_SCORE} INTEGER," +
        "${DevicePreviewColumns.COLUMN_REPARABILITY} FLOAT," +
        "${DevicePreviewColumns.COLUMN_PROCESSOR} INTEGER," +
        "${DevicePreviewColumns.COLUMN_RAM} INTEGER)"
