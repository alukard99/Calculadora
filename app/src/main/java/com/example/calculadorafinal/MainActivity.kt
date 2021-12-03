package com.example.calculadorafinal

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var num1: Double = 0.0
    private var num2: Double = 0.0
    private var operacion: Int = 0
    private var modo: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(modo == 1){
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false
            button5.isEnabled = false
            button6.isEnabled = false
            button7.isEnabled = false
            button8.isEnabled = false
            button9.isEnabled = false
        }
        if(modo == 2){
            button2.isEnabled = true
            button3.isEnabled = true
            button4.isEnabled = true
            button5.isEnabled = true
            button6.isEnabled = true
            button7.isEnabled = true
            button8.isEnabled = true
            button9.isEnabled = true
        }
        if(modo == 0 || modo == 1){
            buttonA.isEnabled = false
            buttonB.isEnabled = false
            buttonCH.isEnabled = false
            buttonD.isEnabled = false
            buttonE.isEnabled = false
            buttonF.isEnabled = false
        }

        button1.setOnClickListener{intrNum("1")}
        button2.setOnClickListener{intrNum("2")}
        button3.setOnClickListener{intrNum("3")}
        button4.setOnClickListener{intrNum("4")}
        button5.setOnClickListener{intrNum("5")}
        button6.setOnClickListener{intrNum("6")}
        button7.setOnClickListener{intrNum("7")}
        button8.setOnClickListener{intrNum("8")}
        button9.setOnClickListener{intrNum("9")}
        button0.setOnClickListener{intrNum("0")}
        buttonA.setOnClickListener { intrChar("a") }
        buttonB.setOnClickListener { intrChar("b") }
        buttonCH.setOnClickListener { intrChar("c") }
        buttonD.setOnClickListener { intrChar("d") }
        buttonE.setOnClickListener { intrChar("e") }
        buttonF.setOnClickListener { intrChar("f") }
        buttonHEX.setOnClickListener{cambiarModo()}
        buttonPlus.setOnClickListener{ funcion(SUMA)}
        buttonMinus.setOnClickListener{ funcion(RESTA)}
        buttonPor.setOnClickListener{ funcion(MULT)}
        buttonDiv.setOnClickListener{ funcion(DIV)}


        buttonCE.setOnClickListener{
            num1 = 0.0
            num2 = 0.0
            textView.text = ""
            operacion = NULO
        }
        buttonAC.setOnClickListener{
            textView.text = ""
        }
        buttonDot.setOnClickListener{
            var delTextView = textView.text.toString()

            delTextView += "."

            textView.text = delTextView
        }
        buttonIgual.setOnClickListener{
            if(num1 == num2 && num1!=0.0){
                textView.text = "0"
            }
            if(modo==1){
                var resultado = when(operacion) {
                    SUMA -> num1 + num2
                    RESTA -> num1 - num2
                    MULT -> num1 * num2
                    DIV -> num1 / num2
                    else -> 0
                }
                textView.text = Integer.toBinaryString(resultado.toInt())


            }
            if(modo==0){
                var resultado = when(operacion) {
                    SUMA -> num1 + num2
                    RESTA -> num1 - num2
                    MULT -> num1 * num2
                    DIV -> num1 / num2
                    else -> 0
                }
                textView.text = resultado.toString()
            }
            if(modo==2){
                var resultado = when(operacion){
                    SUMA -> num1 + num2
                    RESTA -> num1 - num2
                    MULT -> num1 * num2
                    DIV -> num1 / num2

                    else -> 0
                }
                textView.text = Integer.toHexString(resultado.toInt()).toString()
                }

            }
    }

    private fun cambiarModo(){
        modo += 1
        convertirNumero()
        if(modo == 3){
            modo=0
            if(textView.text != ""){

            }
            else{
            toDecimal(textView.text.toString())}
            Toast.makeText(this, "Decimal", Toast.LENGTH_SHORT).show()
        }
//asdasdas

        habilitarBotones()
    }
    private fun toDecimal(numero: String){
        //Convertir de hexadecimal a decimal
        var numDecimal = Integer.parseInt(numero, 16)
        textView.text = numDecimal.toString()
    }
    private fun convertirNumero(){
        textView.text = ""
    }
    private fun toBinario(numero: String){
        var numBinario = Integer.toBinaryString(numero.toInt())
        textView.text = numBinario.toString()
    }
    fun convertBinaryToDecimal(num: Long): Int {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }
    private fun toHexa(numero: String) {
        if(textView.text == ""){

        }
        else{
            var numHexa = Integer.toHexString(convertBinaryToDecimal(numero.toLong()))
        textView.text = numHexa.toString()}
    }
    private fun habilitarBotones(){

        if(modo == 0 || modo == 1){
            buttonA.isEnabled = false
            buttonB.isEnabled = false
            buttonCH.isEnabled = false
            buttonD.isEnabled = false
            buttonE.isEnabled = false
            buttonF.isEnabled = false
        }
        if(modo==1) {
            Toast.makeText(this, "Binario" ,Toast.LENGTH_SHORT).show()
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false
            button5.isEnabled = false
            button6.isEnabled = false
            button7.isEnabled = false
            button8.isEnabled = false
            button9.isEnabled = false
        }
        if(modo==2) {
            Toast.makeText(this, "Hexadecimal" ,Toast.LENGTH_SHORT).show()
            button2.isEnabled = true
            button3.isEnabled = true
            button4.isEnabled = true
            button5.isEnabled = true
            button6.isEnabled = true
            button7.isEnabled = true
            button8.isEnabled = true
            button9.isEnabled = true
            buttonA.isEnabled = true
            buttonB.isEnabled = true
            buttonCH.isEnabled = true
            buttonD.isEnabled = true
            buttonE.isEnabled = true
            buttonF.isEnabled = true
            }
    }

    private fun intrNum(num: String){
        textView.text = "${textView.text}$num"
        if(operacion == NULO){
            if(modo==0){
                num2 = textView.text.toString().toDouble()
            }
            if(modo==1){
                num2 = Integer.parseInt(textView.text.toString(),2).toDouble()
            }
            if(modo==2){
                num2 = Integer.parseInt(textView.text.toString(),16).toDouble()
            }
        }
        else{
            if(modo==0){
                num2 = textView.text.toString().toDouble()
            }
            if(modo==1){
                num2 = Integer.parseInt(textView.text.toString(),2).toDouble()
            }
            if(modo==2){
                num2 = Integer.parseInt(textView.text.toString(),16).toDouble()
            }

        }
    }
    private fun intrChar(char: String){

        if(operacion == NULO){
            num1 = Integer.parseInt(char, 16).toDouble()
        }
        else{
            num2 = Integer.parseInt(char,16).toDouble()
        }
        textView.append(char)
    }
    private fun funcion(operacion: Int){
        if(modo==0) {
            this.operacion = operacion
            num1 = textView.text.toString().toDouble()
            textView.text = ""
        }
        if(modo==1){
            this.operacion = operacion
            num1 = Integer.parseInt(textView.text.toString(), 2).toDouble()
            textView.text = ""
        }
        if(modo==2){
            this.operacion = operacion
            num1 = Integer.parseInt(textView.text.toString(),16).toDouble()
            textView.text = ""
        }
    }
    companion object{
        const val NULO = 0
        const val SUMA = 1
        const val RESTA = 2
        const val MULT = 3
        const val DIV = 4
    }
}