package com.example.techgarden.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.techgarden.Computer
import com.example.techgarden.Device
import com.example.techgarden.DeviceRegister
import com.example.techgarden.SmartPhone
import com.example.techgarden.State
import com.example.techgarden.Tablet
import com.example.techgarden.TypeDevice
import com.example.techgarden.databinding.ActivityHomeTechGardenBinding
import java.sql.Timestamp
import java.util.UUID

class HomeTechGarden : AppCompatActivity() {

    private lateinit var binding: ActivityHomeTechGardenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeTechGardenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val etDevice = binding.deviceSpinner
        val etBrand = binding.etBrand
        val etModel = binding.etModel
        val etState = binding.stateSpinner
        //val tvResult = binding.tvResult


        binding.btnShowDeviceDetail.setOnClickListener {
            val allDevice = DeviceRegister.allDevice
            var listDevice: MutableList<String> = mutableListOf()

            allDevice.forEach {
                val findDevice = showDeviceDetail(it)
                listDevice.add(findDevice)
            }
            binding.tvResult.text = listDevice.toString()
        }
        //Funcion
        binding.btnAddDevice.setOnClickListener {
            val brand = etBrand.text.toString()
            val model = etModel.text.toString()
            val state = State.valueOf(etState.toString())
            val device = TypeDevice.valueOf(etDevice.toString())
            addDevice(device, brand, model, state)
            cleanField()
        }

        binding.btnRemoveDevice.setOnClickListener {
            val brand = etBrand.text.toString()
            val model = etModel.text.toString()
            val device = TypeDevice.valueOf(etDevice.toString())
            val devices = DeviceRegister.allDevice
            val id = ""
            removeDevice(id)
        }

        binding.btnSearchDevice.setOnClickListener {
            val devices = searchDeviceModel()
            binding.tvResult.text = devices
        }

    }
    //Funcion para añadir dispositivo segun su tipo
    private fun addDevice(device: TypeDevice, brand: String, model: String, state: State){
        val deviceAdd: Device
        val timestamp = Timestamp(System.currentTimeMillis())
        val str = timestamp.toString() + packageName
        val uuid = UUID.nameUUIDFromBytes(str.toByteArray()).toString() //Permite identificar como objeto unico

        when (device) {
            TypeDevice.COMPUTER -> saveObjectDevice(Computer(brand=brand, model=model, state=state, id=uuid))
            TypeDevice.TABLET -> saveObjectDevice(Tablet(brand=brand, model=model, state=state, id=uuid))
            TypeDevice.SMARTPHONE -> saveObjectDevice(SmartPhone(brand=brand, model=model, state=state, id=uuid))
        }

    }

    private fun saveObjectDevice(device: Device) {
        var tDadd = DeviceRegister
        tDadd.allDevice.add(device)
    }

    private fun removeDevice(id: String) {
        var tDrem = DeviceRegister.allDevice
        tDrem.forEach {
            if (it.id == id) {
                tDrem.remove(it)
            }
        }
    }

    //Funcion que muestra los dispositivos detallados empleando polimorfismo
    private fun showDeviceDetail(device: Device): String{
        val detail = device.getDevice()
        return detail
    }

    //Funcion que busca los dispositivos por modelo
    private fun searchDeviceModel(): String{
        val result = StringBuilder("lista de dispositivos registrados:\n")
        SmartPhone.listSmartPhone.forEach { device ->
            result.append("$device\n")
        }
        return result.toString()
    }

    //Funcion para limpiar campos edit text
    fun cleanField() {
        binding.apply {
            etBrand.text.clear()
            etModel.text.clear()
            etSearchModel.text.clear()
        }
    }


}