package com.example.techgarden.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.Specification
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

        val etDevice = binding.deviceSpinner //Obtencion valor del campo Tipo de Dispositivo
        val etBrand = binding.etBrand //Obtencion valor del campo Marca
        val etModel = binding.etModel //Obtencion valor del campo Modelo
        val etState = binding.stateSpinner //Obtencion valor del campo Estado del Dispositivo

        //Ejecucion al tocar boton ShowDeviceDetail
        binding.btnShowDeviceDetail.setOnClickListener {
            val allDevice = DeviceRegister.allDevice
            val listDevice: MutableList<String> = mutableListOf()

            allDevice.forEach {
                val findDevice = showDeviceDetail(it)
                listDevice.add(findDevice)
            }
            binding.btnContinue.visibility = View.VISIBLE
            binding.tvResult.visibility = View.VISIBLE
            binding.tvResult.text = listDevice.joinToString("\n")
            binding.btnShowDeviceDetail.visibility = View.GONE
            binding.radioGroup.visibility = View.GONE
        }

        //Ejecucion al tocar boton AddDevice
        binding.btnAddDevice.setOnClickListener {
            val brand = etBrand.text.toString()
            val model = etModel.text.toString()
            val state = State.valueOf(etState.getSelectedItem().toString())
            val device = TypeDevice.valueOf(etDevice.getSelectedItem().toString())
            addDevice(device, brand, model, state)
            cleanField()
        }

        //Ejecucion al tocar boton RemoveDevice
        binding.btnRemoveDevice.setOnClickListener {
            removeDevice()
            cleanField()
        }

        //Ejecucion al tocar boton ModifyDevice
        binding.btnModifyDevice.setOnClickListener {
            modifyDevice()
            cleanField()
        }

        //Ejecucion al tocar boton Continue
        binding.btnContinue.setOnClickListener {
            cleanField()
        }

        //Ejecucion al seleccionar accion Adicionar
        binding.radioAdd.setOnClickListener {
            selDevice(action = "add")
        }

        //Ejecucion al seleccionar accion Modificar
        binding.radioModify.setOnClickListener {
            selDevice(action = "update")
        }

        //Ejecucion al seleccionar accion Eliminar
        binding.radioDel.setOnClickListener {
            selDevice(action = "delete")
        }

        //Ejecucion al seleccionar accion Mostrar Detalles
        binding.radioDetail.setOnClickListener {
            selDevice(action = "detail")
        }

        //Ejecucion al aceptar la seleccion de tipo de dispositivo para añadir
        binding.btnSelspinneradd.setOnClickListener {
            val devSpinner = TypeDevice.valueOf(etDevice.getSelectedItem().toString())

            when (devSpinner) {
                TypeDevice.ORDENADOR -> {
                    binding.btnAddDevice.visibility = View.VISIBLE
                    binding.deviceSpinner.visibility = View.GONE
                    binding.tvDevice.visibility = View.GONE
                    binding.stateSpinner.visibility = View.VISIBLE
                    binding.tvState.visibility = View.VISIBLE
                    binding.dualsimSpinner.visibility = View.GONE
                    binding.tvDualsim.visibility = View.GONE
                    binding.etBrand.visibility = View.VISIBLE
                    binding.etModel.visibility = View.VISIBLE
                    binding.etCpu.visibility = View.VISIBLE
                    binding.etRam.visibility = View.VISIBLE
                    binding.etImei.visibility = View.GONE
                    binding.etPantalla.visibility = View.GONE
                    binding.radioGroup.visibility = View.GONE
                    binding.btnSelspinneradd.visibility = View.GONE
                    binding.btnSelspinnerupdate.visibility = View.GONE
                    binding.tvResult.visibility = View.GONE
                }

                TypeDevice.TABLETA -> {
                    binding.btnAddDevice.visibility = View.VISIBLE
                    binding.deviceSpinner.visibility = View.GONE
                    binding.tvDevice.visibility = View.GONE
                    binding.stateSpinner.visibility = View.VISIBLE
                    binding.tvState.visibility = View.VISIBLE
                    binding.dualsimSpinner.visibility = View.GONE
                    binding.tvDualsim.visibility = View.GONE
                    binding.etBrand.visibility = View.VISIBLE
                    binding.etModel.visibility = View.VISIBLE
                    binding.etCpu.visibility = View.GONE
                    binding.etRam.visibility = View.GONE
                    binding.etImei.visibility = View.GONE
                    binding.etPantalla.visibility = View.VISIBLE
                    binding.radioGroup.visibility = View.GONE
                    binding.btnSelspinneradd.visibility = View.GONE
                    binding.btnSelspinnerupdate.visibility = View.GONE
                    binding.tvResult.visibility = View.GONE
                }

                TypeDevice.TELEFONO_INTELIGENTE -> {
                    binding.btnAddDevice.visibility = View.VISIBLE
                    binding.deviceSpinner.visibility = View.GONE
                    binding.tvDevice.visibility = View.GONE
                    binding.stateSpinner.visibility = View.VISIBLE
                    binding.tvState.visibility = View.VISIBLE
                    binding.dualsimSpinner.visibility = View.VISIBLE
                    binding.tvDualsim.visibility = View.VISIBLE
                    binding.etBrand.visibility = View.VISIBLE
                    binding.etModel.visibility = View.VISIBLE
                    binding.etCpu.visibility = View.GONE
                    binding.etRam.visibility = View.GONE
                    binding.etImei.visibility = View.VISIBLE
                    binding.etPantalla.visibility = View.GONE
                    binding.radioGroup.visibility = View.GONE
                    binding.btnSelspinneradd.visibility = View.GONE
                    binding.btnSelspinnerupdate.visibility = View.GONE
                    binding.tvResult.visibility = View.GONE
                }
            }

        }

        //Ejecucion al aceptar la seleccion de tipo de dispositivo para añadir
        binding.btnSelspinnerupdate.setOnClickListener {
            val devSpinner = TypeDevice.valueOf(etDevice.getSelectedItem().toString())

            when (devSpinner) {
                TypeDevice.ORDENADOR -> {
                    binding.btnAddDevice.visibility = View.GONE
                    binding.btnModifyDevice.visibility = View.VISIBLE
                    binding.deviceSpinner.visibility = View.GONE
                    binding.tvDevice.visibility = View.GONE
                    binding.stateSpinner.visibility = View.VISIBLE
                    binding.tvState.visibility = View.VISIBLE
                    binding.dualsimSpinner.visibility = View.GONE
                    binding.tvDualsim.visibility = View.GONE
                    binding.etBrand.visibility = View.VISIBLE
                    binding.etModel.visibility = View.VISIBLE
                    binding.etCpu.visibility = View.VISIBLE
                    binding.etRam.visibility = View.VISIBLE
                    binding.etImei.visibility = View.GONE
                    binding.etPantalla.visibility = View.GONE
                    binding.etDeviceToModify.visibility = View.VISIBLE
                    binding.radioGroup.visibility = View.GONE
                    binding.btnSelspinneradd.visibility = View.GONE
                    binding.btnSelspinnerupdate.visibility = View.GONE
                    binding.tvResult.visibility = View.GONE
                }

                TypeDevice.TABLETA -> {
                    binding.btnAddDevice.visibility = View.GONE
                    binding.btnModifyDevice.visibility = View.VISIBLE
                    binding.deviceSpinner.visibility = View.GONE
                    binding.tvDevice.visibility = View.GONE
                    binding.stateSpinner.visibility = View.VISIBLE
                    binding.tvState.visibility = View.VISIBLE
                    binding.dualsimSpinner.visibility = View.GONE
                    binding.tvDualsim.visibility = View.GONE
                    binding.etBrand.visibility = View.VISIBLE
                    binding.etModel.visibility = View.VISIBLE
                    binding.etCpu.visibility = View.GONE
                    binding.etRam.visibility = View.GONE
                    binding.etImei.visibility = View.GONE
                    binding.etPantalla.visibility = View.VISIBLE
                    binding.etDeviceToModify.visibility = View.VISIBLE
                    binding.radioGroup.visibility = View.GONE
                    binding.btnSelspinneradd.visibility = View.GONE
                    binding.btnSelspinnerupdate.visibility = View.GONE
                    binding.tvResult.visibility = View.GONE
                }

                TypeDevice.TELEFONO_INTELIGENTE -> {
                    binding.btnAddDevice.visibility = View.GONE
                    binding.btnModifyDevice.visibility = View.VISIBLE
                    binding.deviceSpinner.visibility = View.GONE
                    binding.tvDevice.visibility = View.GONE
                    binding.stateSpinner.visibility = View.VISIBLE
                    binding.tvState.visibility = View.VISIBLE
                    binding.dualsimSpinner.visibility = View.VISIBLE
                    binding.tvDualsim.visibility = View.VISIBLE
                    binding.etBrand.visibility = View.VISIBLE
                    binding.etModel.visibility = View.VISIBLE
                    binding.etCpu.visibility = View.GONE
                    binding.etRam.visibility = View.GONE
                    binding.etImei.visibility = View.VISIBLE
                    binding.etPantalla.visibility = View.GONE
                    binding.etDeviceToModify.visibility = View.VISIBLE
                    binding.radioGroup.visibility = View.GONE
                    binding.btnSelspinneradd.visibility = View.GONE
                    binding.btnSelspinnerupdate.visibility = View.GONE
                    binding.tvResult.visibility = View.GONE
                }
            }

        }
    }

    //Funcion para limpiar pantalla dependiedo del Radio Button seleccionado
    private fun selDevice(action: String) {
        binding.deviceSpinner.visibility = View.VISIBLE
        binding.tvDevice.visibility = View.VISIBLE
        binding.btnAddDevice.visibility = View.GONE
        binding.stateSpinner.visibility = View.GONE
        binding.tvState.visibility = View.GONE
        binding.dualsimSpinner.visibility = View.GONE
        binding.tvDualsim.visibility = View.GONE
        binding.etBrand.visibility = View.GONE
        binding.etModel.visibility = View.GONE
        binding.etCpu.visibility = View.GONE
        binding.etRam.visibility = View.GONE
        binding.etImei.visibility = View.GONE
        binding.etPantalla.visibility = View.GONE
        binding.etDeviceToModify.visibility = View.GONE
        binding.radioGroup.visibility = View.GONE
        binding.tvResult.visibility = View.GONE
        binding.btnRemoveDevice.visibility = View.GONE
        binding.btnShowDeviceDetail.visibility = View.GONE
        when (action) {
            "add" -> binding.btnSelspinneradd.visibility = View.VISIBLE
            "update" -> binding.btnSelspinnerupdate.visibility = View.VISIBLE
            "delete" -> { binding.deviceSpinner.visibility = View.GONE
                binding.tvDevice.visibility = View.GONE
                binding.etDeviceToModify.visibility = View.VISIBLE
                binding.btnRemoveDevice.visibility = View.VISIBLE
            }
            "detail" -> { binding.btnShowDeviceDetail.visibility = View.VISIBLE
                binding.deviceSpinner.visibility = View.GONE
                binding.tvDevice.visibility = View.GONE
               }
        }
    }

    //Funcion para añadir dispositivo segun su tipo
    private fun addDevice(device: TypeDevice, brand: String, model: String, state: State) {
        val timestamp = Timestamp(System.currentTimeMillis())
        val str = timestamp.toString() + packageName
        val uuid = UUID.nameUUIDFromBytes(str.toByteArray())
            .toString() //Permite identificar como objeto unico

        when (device) {
            TypeDevice.ORDENADOR -> { val comp =  Computer(
                brand = brand,
                model = model,
                state = state)
                saveObjectDevice(comp, uuid)
                comp.setspecificationComputer(
                    Specification(
                        binding.etCpu.text.toString(),
                        binding.etRam.text.toString(), "", "", "",
                    )
                )
            }

            TypeDevice.TABLETA -> { val tab = Tablet(
                brand = brand,
                model = model,
                state = state )
                saveObjectDevice(tab, uuid
                )
                tab.setspecificationTablet(
                    Specification(
                        "",
                        "",
                        "",
                        "",
                        binding.etPantalla.text.toString()
                    )
                )
            }

            TypeDevice.TELEFONO_INTELIGENTE -> { val smrt = SmartPhone(
                brand = brand,
                model = model,
                state = state)
                saveObjectDevice(smrt, uuid)
                smrt.setspecificationSmatrphone(
                    Specification(
                        "",
                        "",
                        binding.etImei.text.toString(),
                        binding.dualsimSpinner.getSelectedItem().toString(),
                        ""
                    )
                )
            }
        }

    }

    //Funcion para almacenar los dispositivos en la Object Class
    private fun saveObjectDevice(device: Device, id: String) {
        val tDadd = DeviceRegister
        device.setId(id)
        tDadd.allDevice.add(device)
    }

    //Funcio para eliminar dispositivo de la Object Class
    private fun removeDevice() {
        val id = binding.etDeviceToModify.text.toString()
        val devices = DeviceRegister.allDevice
        for (device in devices) {
            if (device.getId() == id) {
                devices.remove(device)
                break
            }
        }

    }

    //Funcion para mostrar los dispositivos detallados empleando polimorfismo
    private fun showDeviceDetail(device: Device): String {
        return device.getDevice()
    }

    //Funcion para actualizar los dispositivos de la Object Class
    private fun modifyDevice() {
        val id = binding.etDeviceToModify.text.toString()
        val brand = binding.etBrand.text.toString()
        val model = binding.etModel.text.toString()
        val state = State.valueOf(binding.stateSpinner.getSelectedItem().toString())
        val devices = DeviceRegister.allDevice
        for (it in devices) {
            if (it.getId() == id) {
                it.brand = brand
                it.model = model
                it.state = state
                when (it) {
                    is Computer -> { it.setspecificationComputer(
                                Specification(
                                binding.etCpu.text.toString(),
                                binding.etRam.text.toString(), "", "", "",
                            )
                        )

                    }
                    is Tablet -> { it.setspecificationTablet(
                            Specification(
                            "",
                            "",
                            "",
                            "",
                            binding.etPantalla.text.toString()
                        )
                    )
                    }
                    is SmartPhone -> { it.setspecificationSmatrphone(
                            Specification(
                            "",
                            "",
                            binding.etImei.text.toString(),
                            binding.dualsimSpinner.getSelectedItem().toString(),
                            ""
                        )
                    )
                    }
                }
                break
            }
        }

    }

    //Funcion para limpiar campos edit text de la vista
    private fun cleanField() {
        binding.apply {
            etBrand.text.clear()
            etModel.text.clear()
            etDeviceToModify.text.clear()
            etCpu.text.clear()
            etRam.text.clear()
            etImei.text.clear()
            etPantalla.text.clear()
            radioGroup.visibility = View.VISIBLE
            btnAddDevice.visibility = View.GONE
            btnModifyDevice.visibility = View.GONE
            btnRemoveDevice.visibility = View.GONE
            btnContinue.visibility = View.GONE
            deviceSpinner.visibility = View.GONE
            stateSpinner.visibility = View.GONE
            dualsimSpinner.visibility = View.GONE
            etBrand.visibility = View.GONE
            etModel.visibility = View.GONE
            etDeviceToModify.visibility = View.GONE
            etCpu.visibility = View.GONE
            etRam.visibility = View.GONE
            etImei.visibility = View.GONE
            etPantalla.visibility = View.GONE
            tvResult.visibility = View.GONE
            tvDevice.visibility = View.GONE
            tvState.visibility = View.GONE
            tvDualsim.visibility = View.GONE
        }
    }
//funcion para ocultar el teclado
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

}