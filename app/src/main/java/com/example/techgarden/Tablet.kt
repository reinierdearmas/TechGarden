package com.example.techgarden

import com.example.Specification

class Tablet(brand: String, model: String, state: State): Device(brand,model,state) {

    private var specificationTablet: Specification = Specification("", "", "", "", "")

    fun getspecificationTablet(): Specification{
        return this.specificationTablet
    }

    fun setspecificationTablet(specificationTabletNew: Specification){
        this.specificationTablet = specificationTabletNew
    }

}