package com.cafein.uts_tbk_android_kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    private var valueOne: Double = Double.NaN
    private var valueTwo: Double = Double.NaN
    private var operator: String = ""
    private var valueTxt: String = ""
    private var isDecimal: Boolean = false

    private lateinit var display_one: TextView
    private lateinit var display_two: TextView
    private lateinit var btn_one: Button
    private lateinit var btn_two: Button
    private lateinit var btn_three: Button
    private lateinit var btn_four: Button
    private lateinit var btn_five: Button
    private lateinit var btn_six: Button
    private lateinit var btn_seven: Button
    private lateinit var btn_eight: Button
    private lateinit var btn_nine: Button
    private lateinit var btn_zero: Button
    private lateinit var btn_decimal: Button
    private lateinit var btn_clear: Button
    private lateinit var btn_delete: Button
    private lateinit var btn_add: Button
    private lateinit var btn_subtract: Button
    private lateinit var btn_multiply: Button
    private lateinit var btn_divide: Button
    private lateinit var btn_equal: Button

    private fun initComponents() {
        display_one = findViewById(R.id.display_one)
        display_two = findViewById(R.id.display_two)
        btn_one = findViewById(R.id.btn_one)
        btn_two = findViewById(R.id.btn_two)
        btn_three = findViewById(R.id.btn_three)
        btn_four = findViewById(R.id.btn_four)
        btn_five = findViewById(R.id.btn_five)
        btn_six = findViewById(R.id.btn_six)
        btn_seven = findViewById(R.id.btn_seven)
        btn_eight = findViewById(R.id.btn_eight)
        btn_nine = findViewById(R.id.btn_nine)
        btn_zero = findViewById(R.id.btn_zero)
        btn_decimal = findViewById(R.id.btn_decimal)
        btn_clear = findViewById(R.id.btn_clear)
        btn_delete = findViewById(R.id.btn_delete)
        btn_add = findViewById(R.id.btn_add)
        btn_subtract = findViewById(R.id.btn_subtract)
        btn_multiply = findViewById(R.id.btn_multiply)
        btn_divide = findViewById(R.id.btn_divide)
        btn_equal = findViewById(R.id.btn_equal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        btn_one.setOnClickListener { appendValue("1") }
        btn_two.setOnClickListener { appendValue("2") }
        btn_three.setOnClickListener { appendValue("3") }
        btn_four.setOnClickListener { appendValue("4") }
        btn_five.setOnClickListener { appendValue("5") }
        btn_six.setOnClickListener { appendValue("6") }
        btn_seven.setOnClickListener { appendValue("7") }
        btn_eight.setOnClickListener { appendValue("8") }
        btn_nine.setOnClickListener { appendValue("9") }
        btn_zero.setOnClickListener { appendValue("0") }
        btn_add.setOnClickListener { setOperator("+") }
        btn_subtract.setOnClickListener { setOperator("-") }
        btn_multiply.setOnClickListener { setOperator("*") }
        btn_divide.setOnClickListener { setOperator("/") }
        btn_equal.setOnClickListener{ calculateResult() }
        btn_decimal.setOnClickListener { decimalValue() }
        btn_delete.setOnClickListener { deleteValue() }
        btn_clear.setOnClickListener { clearAll() }
    }
    private fun appendValue(value: String) {
        valueTxt += value
        if (operator == "") {
            if (display_two.text !== "") { clearDisplay() }
            valueOne = valueTxt.toDouble()
        } else {
            valueTwo = valueTxt.toDouble()
        }
        val lastDisplayOne = display_one.text
        display_one.text = "$lastDisplayOne$value"
    }
    private fun setOperator(op: String) {
        if (operator == "") {
            operator = op
            if (display_two.text == "") {
                val lastDisplayOne = display_one.text
                display_one.text = "$lastDisplayOne$operator"
            } else {
                display_one.text = display_two.text.toString() + operator
                valueOne = display_two.text.toString().toDouble()
            }
            valueTxt = ""
            isDecimal = false
        }
    }
    private fun decimalValue() {
        if (!isDecimal && !valueOne.isNaN()) {
            isDecimal = true
            appendValue(".")
        }
    }
    private fun deleteValue() {
        val n = display_one.text.length
        if (!valueOne.isNaN()) {
            val lastDisplay = display_one.text[n-1].toString()
            val newInput = display_one.text.dropLast(1)
            display_one.text = newInput
            if (lastDisplay == "+" || lastDisplay == "-" || lastDisplay == "*" || lastDisplay == "/") {
                operator = ""
                valueTxt = display_one.text.toString()
            } else {
                val updateValueTxt = valueTxt.dropLast(1)
                valueTxt = updateValueTxt
                if (operator == "") {
                    valueOne = if (valueTxt != "") {
                        valueTxt.toDouble()
                    } else {
                        0.0
                    }
                } else {
                    valueTwo = if (valueTxt != "") {
                        valueTxt.toDouble()
                    } else {
                        0.0
                    }
                }
            }
        }
    }
    private fun calculateResult() {
        if (operator != "") {
            var result = 0.0
            when (operator) {
                "+" -> result = valueOne + valueTwo
                "-" -> result = valueOne - valueTwo
                "*" -> result = valueOne * valueTwo
                "/" -> result = valueOne / valueTwo
            }
            showResult(result)
            clearResult()
        }
    }
    private fun showResult(value: Double) {
        val valueInt = value.toInt()
        if (value - valueInt.toDouble() == 0.0) {
            display_two.text = "$valueInt"
        } else {
            display_two.text = "$value"
        }
    }
    private fun clearResult() {
        clearValue()
        isDecimal = false
        operator = ""
        valueTxt = ""
    }
    private fun clearDisplay() {
        display_one.text = ""
        display_two.text = ""
    }
    private fun clearValue() {
        valueOne = Double.NaN
        valueTwo = Double.NaN
    }
    private fun clearAll() {
        clearResult()
        clearDisplay()
    }
}