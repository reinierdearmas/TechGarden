package com.example.techgarden

open class Device(val brand: String, val model: String, val state: State) {

    val typeDevice = ""
    val id = ""

    fun getDevice(): String {
        var detail = ""
        when (this) {
            is Computer -> detail = TypeDevice.COMPUTER.toString() + this.id + this.brand + this.model + this.cpu + this.ram + "\n"
            is SmartPhone -> detail = TypeDevice.SMARTPHONE.toString() + this.id + this.brand + this.model + this.imei + "\n"
            is Tablet -> detail = TypeDevice.TABLET.toString() + this.id + this.brand + this.model + this.pantalla + "\n"
        }
        return detail
    }

}