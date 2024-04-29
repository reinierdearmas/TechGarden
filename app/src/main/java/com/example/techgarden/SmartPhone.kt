package com.example.techgarden

import android.util.Log
import com.example.Specification

class SmartPhone(brand: String, model: String, state: State) : Device(brand, model, state) {

    private var specificationSmatrphone: Specification = Specification("", "", "", "", "")

    fun getspecificationSmatrphone(): Specification{
        return this.specificationSmatrphone
    }

    fun setspecificationSmatrphone(specificationSmatrphoneNew: Specification){
        this.specificationSmatrphone = specificationSmatrphoneNew
    }

}