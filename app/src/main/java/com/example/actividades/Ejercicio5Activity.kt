package com.example.actividades

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio5Activity : AppCompatActivity() {

    private lateinit var tvPantalla: TextView
    private var primerOperando: Double? = null
    private var operacionPendiente: String? = null
    private var reiniciarPantalla = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        tvPantalla = findViewById(R.id.tvPantallaCalc)

        val botonesNumericos = listOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8",
            R.id.btn9 to "9"
        )

        for ((id, digito) in botonesNumericos) {
            findViewById<Button>(id).setOnClickListener { pulsarDigito(digito) }
        }

        findViewById<Button>(R.id.btnPunto).setOnClickListener { pulsarPunto() }
        findViewById<Button>(R.id.btnLimpiar).setOnClickListener { limpiar() }

        findViewById<Button>(R.id.btnSum).setOnClickListener { seleccionarOperacion("+") }
        findViewById<Button>(R.id.btnRes).setOnClickListener { seleccionarOperacion("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { seleccionarOperacion("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { seleccionarOperacion("/") }

        findViewById<Button>(R.id.btnIgual).setOnClickListener { calcularResultado() }
    }

    private fun pulsarDigito(digito: String) {
        val actual = tvPantalla.text.toString()
        if (reiniciarPantalla || actual == "0" || actual == "Error") {
            tvPantalla.text = digito
            reiniciarPantalla = false
        } else {
            tvPantalla.text = actual + digito
        }
    }

    private fun pulsarPunto() {
        val actual = tvPantalla.text.toString()
        if (reiniciarPantalla || actual == "Error") {
            tvPantalla.text = "0."
            reiniciarPantalla = false
        } else if (!actual.contains(".")) {
            tvPantalla.text = "$actual."
        }
    }

    private fun seleccionarOperacion(op: String) {
        val valorActual = tvPantalla.text.toString().toDoubleOrNull() ?: return
        if (primerOperando != null && operacionPendiente != null && !reiniciarPantalla) {
            calcularResultado()
            primerOperando = tvPantalla.text.toString().toDoubleOrNull()
        } else {
            primerOperando = valorActual
        }
        operacionPendiente = op
        reiniciarPantalla = true
    }

    private fun calcularResultado() {
        val op1 = primerOperando ?: return
        val op = operacionPendiente ?: return
        val op2 = tvPantalla.text.toString().toDoubleOrNull() ?: return

        val resultado = when (op) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "/" -> if (op2 == 0.0) null else op1 / op2
            else -> null
        }

        if (resultado == null) {
            tvPantalla.text = "Error"
        } else {
            tvPantalla.text = if (resultado % 1.0 == 0.0) {
                resultado.toLong().toString()
            } else {
                resultado.toString()
            }
        }

        primerOperando = null
        operacionPendiente = null
        reiniciarPantalla = true
    }

    private fun limpiar() {
        tvPantalla.text = "0"
        primerOperando = null
        operacionPendiente = null
        reiniciarPantalla = false
    }
}