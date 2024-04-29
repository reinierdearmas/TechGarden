package com.example.techgarden

open class Device(var brand: String, var model: String, var state: State) {

    private var id = ""

    fun getDevice(): String {
        var detail = ""
        when (this) {
            is Computer -> detail = "Marca: ${brand}, Modelo: ${model}, Estado: ${state}, CPU: ${this.getspecificationComputer().cpu}, RAM: ${this.getspecificationComputer().ram}GB \n"
            is SmartPhone -> detail = "Marca: ${this.brand}, Modelo: ${this.model}, Estado: ${this.state}, IMEI: ${this.getspecificationSmatrphone().imei} \n"
            is Tablet -> detail = "Marca: ${this.brand}, Modelo: ${this.model}, Estado: ${this.state}, Pantalla: ${this.getspecificationTablet().pantalla} \n"
        }
        return detail
    }


    fun setId(newId: String){
       this.id = newId
        }

    fun getId(): String{
        return this.id
    }

}