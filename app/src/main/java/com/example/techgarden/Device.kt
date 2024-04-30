package com.example.techgarden

open class Device(var brand: String, var model: String, var state: State) {

    private var id = ""

    fun getDevice(): String {
        var detail = ""
        when (this) {
            is Computer -> detail = "Identificador: ${getId()}, Ordenador: Marca: ${brand}, Modelo: ${model}, Estado: ${state}, CPU: ${this.getspecificationComputer().cpu}, RAM: ${getspecificationComputer().ram}GB \n"
            is SmartPhone -> detail = "Identificador: ${getId()}, SmartPhone: Marca: ${brand}, Modelo: ${model}, Estado: ${state}, IMEI: ${getspecificationSmatrphone().imei} \n"
            is Tablet -> detail = "Identificador: ${getId()}, Tablet: Marca: ${brand}, Modelo: ${model}, Estado: ${state}, Pantalla: ${getspecificationTablet().pantalla} \n"
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