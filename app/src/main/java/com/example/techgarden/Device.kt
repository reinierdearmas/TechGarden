package com.example.techgarden

open class Device(val brand: String, val model: String, val state: State) {

    private var id = ""

    fun getDevice(): String {
        var detail = ""
        when (this) {
            is Computer -> detail =
                TypeDevice.ORDENADOR.toString() + "|" + this.getId() + "|" + this.state + "|" + this.brand + "|" + this.model + "|" + this.getspecificationComputer().cpu + "|" + this.getspecificationComputer().ram

            is SmartPhone -> detail =
                TypeDevice.TELEFONO_INTELIGENTE.toString() + "|" + this.getId() + "|" + this.state + "|" + this.brand + "|" + this.model + "|" + this.getspecificationSmatrphone().imei + "|" + this.getspecificationSmatrphone().dualSim

            is Tablet -> detail =
                TypeDevice.TABLETA.toString() + "|" + this.getId() + "|" + this.state + "|" + this.brand + "|" + this.model + "|" + this.getspecificationTablet().pantalla
        }
        return detail
    }

    fun setId(newId: String){
       id = newId
        }

    fun getId(): String{
        return this.id
    }

}