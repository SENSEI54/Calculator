package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var lastDigit : Boolean=false
    var lastDot : Boolean=false
    var subtract : Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastDigit=true
    }

    fun onClr(view: View)
    {
        tvInput.text=""
        lastDigit=false
        lastDot=false
    }

    fun onDecimal(view: View)
    {
        if(lastDigit && !lastDot)
        {
            tvInput.append(".")
            lastDigit=false
            lastDot=true
        }
    }

    fun onEqual(view: View)
    {
        if(lastDigit){
            var tvValue = tvInput.text
            var prefix=""
            if(tvValue.startsWith("-"))
            {
                tvValue=tvValue.substring(1)
                prefix="-"
            }
            try {
                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")

                    var firstValue=splitValue[0]
                    var secondValue=splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        firstValue=prefix+firstValue
                    }

                    tvInput.text=(firstValue.toDouble()-secondValue.toDouble()).toString()
                }

                if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")

                    var firstValue=splitValue[0]
                    var secondValue=splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        firstValue=prefix+firstValue
                    }

                    tvInput.text=(firstValue.toDouble()+secondValue.toDouble()).toString()
                }

                if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")

                    var firstValue=splitValue[0]
                    var secondValue=splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        firstValue=prefix+firstValue
                    }

                    tvInput.text=(firstValue.toDouble()/secondValue.toDouble()).toString()
                }

                if (tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")

                    var firstValue=splitValue[0]
                    var secondValue=splitValue[1]

                    if(!prefix.isEmpty())
                    {
                        firstValue=prefix+firstValue
                    }

                    tvInput.text=(firstValue.toDouble()*secondValue.toDouble()).toString()
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
        else
        {
            Toast.makeText(this,"Invalid Inputs",Toast.LENGTH_LONG).show()
        }

    }

    fun onOperator(view: View){
        if(lastDigit && !isOperatorAdded(tvInput.text.toString()))
        {
            tvInput.append((view as Button).text)
            lastDigit=false
            lastDot=false
        }
    }

    private fun isOperatorAdded(value: String):Boolean
    {
        return if(value.startsWith("-"))
        {
            false
        }else{
            value.contains("/")|| value.contains("*")||
                    value.contains("+")|| value.contains("-")
        }

    }
}