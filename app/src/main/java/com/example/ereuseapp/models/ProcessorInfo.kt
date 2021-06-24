package com.example.ereuseapp.models

import java.io.Serializable

class ProcessorInfo (
    val id: Int,
    val score: Int,
    val manufacturer: String,
    val model: String,
) : Serializable