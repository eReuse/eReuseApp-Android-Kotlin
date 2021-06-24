package com.example.ereuseapp

import com.example.ereuseapp.models.DevicePreview
import com.example.ereuseapp.models.ProcessorInfo
import com.example.ereuseapp.models.RAMInfo
import com.google.gson.Gson
import com.google.gson.JsonArray
import okhttp3.*
import java.io.IOException

class ApiManagement {
    companion object {
        private const val url = "https://ereuse-backend.herokuapp.com"
        private val client = OkHttpClient()

        fun getDevicePreviewByEAN(ean: String, callback: (DevicePreview?, String?) -> Unit) {
            val requestGet = Request.Builder().get().url("$url/device?ean=cs.{$ean}").build()
            client.newCall(requestGet).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body!!
                    val result = Gson().fromJson(responseBody.string(), JsonArray::class.java)

                    var dev: DevicePreview? = null
                    for (device in result) dev = Gson().fromJson(device, DevicePreview::class.java)
                    if (dev != null) callback(dev, null)
                    else callback(null, "no_ean")
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback(null, e.toString())
                }
            })
        }

        fun getDevicesPreviewByType(type: String, callback: (List<DevicePreview>, String?) -> Unit) {
            val requestGet = Request.Builder().get().url("$url/device?device_type=eq.$type").build()
            client.newCall(requestGet).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body!!
                    val result = Gson().fromJson(responseBody.string(), JsonArray::class.java)

                    val devices = mutableListOf<DevicePreview>()
                    for (device in result) devices.add(Gson().fromJson(device, DevicePreview::class.java))

                    callback(devices, null)
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback(mutableListOf(), e.toString())
                }
            })
        }

        fun getDevicesProcessorInfo(id: String, callback: (ProcessorInfo?, String?) -> Unit) {
            val requestGet = Request.Builder().get().url("$url/device?product_id=eq.$id&select=processor(id,manufacturer,model,score)").build()
            client.newCall(requestGet).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body!!
                    val result = Gson().fromJson(responseBody.string(), JsonArray::class.java)
                    val resultObject = result[0].asJsonObject.get("processor").asJsonObject

                    callback(ProcessorInfo(resultObject.get("id").asInt, resultObject.get("score").asInt,
                        resultObject.get("manufacturer").asString, resultObject.get("model").asString), null)
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback(null, e.toString())
                }
            })
        }

        fun getDevicesRAMInfo(id: String, callback: (RAMInfo?, String?) -> Unit) {
            val requestGet = Request.Builder().get().url("$url/device?product_id=eq.$id&select=ram(id,type,info,score)").build()
            client.newCall(requestGet).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body!!
                    val result = Gson().fromJson(responseBody.string(), JsonArray::class.java)
                    val resultObject = result[0].asJsonObject.get("ram").asJsonObject

                    callback(RAMInfo(resultObject.get("id").asInt, resultObject.get("score").asInt,
                        resultObject.get("type").asString, resultObject.get("info").asString), null)
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback(null, e.toString())
                }
            })
        }
    }
}