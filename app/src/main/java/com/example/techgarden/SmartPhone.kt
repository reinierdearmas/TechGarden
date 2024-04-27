package com.example.techgarden

import android.util.Log

class SmartPhone(brand: String, model: String, state: State, id: String) : Device(brand, model, state) {

    val imei: String = ""

    companion object {
        val listSmartPhone = mutableListOf<String>(

            "hola"

        )

    }

}