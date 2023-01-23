package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Button

import com.example.mycalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var lastNumeric :Boolean =false
    var lastDot :Boolean =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view: View){

        binding.tvInput.append((view as Button).text)
        lastNumeric =true
        lastDot =false
    }
    // To clear the screen
    fun onClear(view: View){
        binding.tvInput.text = ""
    }
    //To use the decimal
    fun onDecimal(view: View){
        if(lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastNumeric =false
            lastDot =true
        }


    }
    fun onOperator(view: View){
        if (lastNumeric && isOperatorAdded(binding.tvInput.toString())){
            binding.tvInput.append((view as Button).text)
            lastNumeric =false
            lastDot =false
        }

    }

    fun onEqual(view: View){
        //last number should be number
        if (lastNumeric ){
            //take tvinput and convert it to string
           var tvValue =binding.tvInput.text.toString()
            //crash
            var prefix =""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue =tvValue.substring(1)
                }
                //substration
                if (tvValue.contains("-")){
                        val spitValue = tvValue.split("-")
                        var one =spitValue[0]
                        var two =spitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix +one
                    }

                        binding.tvInput.text =removeZeroAfterDot((one.toDouble() -two.toDouble()).toString())
                }
                //Addition
                else if (tvValue.contains("+")){
                    val spitValue = tvValue.split("+")
                    var one =spitValue[0]
                    var two =spitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix +one
                    }

                    binding.tvInput.text =removeZeroAfterDot((one.toDouble() +two.toDouble()).toString())
                }
                //Multiplication
                else if (tvValue.contains("*")){
                    val spitValue = tvValue.split("*")
                    var one =spitValue[0]
                    var two =spitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix +one
                    }

                    binding.tvInput.text =removeZeroAfterDot((one.toDouble() *two.toDouble()).toString())
                }
                //divide

                else if (tvValue.contains("/")){
                    val spitValue = tvValue.split("/")
                    var one =spitValue[0]
                    var two =spitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix +one
                    }

                    binding.tvInput.text =removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }
            catch (e : java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String) :String{
        var value =result
        if(result.contains(".0"))
            value =result.substring(0 ,result.length-2)
        return value

    }

    private fun isOperatorAdded(value :String) : Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }

    }
}