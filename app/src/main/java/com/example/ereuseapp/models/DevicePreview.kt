package com.example.ereuseapp.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class DevicePreview (
    val product_id: String,
    val release_date: String,
    val image: String,
    val title: String,
    val device_type: String,
    val supplier: String,
    val score: Int,
    val reparability: Float,
    val processor: Int,
    val ram: Int
) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(product_id)
        parcel.writeString(release_date)
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(device_type)
        parcel.writeString(supplier)
        parcel.writeInt(score)
        parcel.writeFloat(reparability)
        parcel.writeInt(processor)
        parcel.writeInt(ram)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DevicePreview> {
        override fun createFromParcel(parcel: Parcel): DevicePreview {
            return DevicePreview(parcel)
        }

        override fun newArray(size: Int): Array<DevicePreview?> {
            return arrayOfNulls(size)
        }
    }
}