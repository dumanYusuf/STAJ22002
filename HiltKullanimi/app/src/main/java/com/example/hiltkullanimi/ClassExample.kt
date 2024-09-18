package com.example.hiltkullanimi

import javax.inject.Inject

class ClassExample
@Inject constructor(private val myInterfaceImpementer:MyInterface)
{
    fun myFunction():String{
        return "working ${myInterfaceImpementer.myPrintFunction()}"
    }
}