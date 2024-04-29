package com.example.techgarden

import com.example.Specification

class Computer(brand: String, model: String, state: State) : Device(brand, model, state) {

    private var specificationComputer: Specification = Specification("", "", "", "", "")

    fun getspecificationComputer(): Specification{
        return this.specificationComputer
    }

    fun setspecificationComputer(specificationComputerNew: Specification){
        this.specificationComputer = specificationComputerNew
    }

}