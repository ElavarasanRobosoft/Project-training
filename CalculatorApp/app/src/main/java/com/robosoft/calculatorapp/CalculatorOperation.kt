package com.robosoft.calculatorapp

sealed class CalculatorOperation(val symbol: String){
    object Add: CalculatorOperation("+")
    object Subract: CalculatorOperation("-")
    object Multiply: CalculatorOperation("x")
    object Divide: CalculatorOperation("/")
}
