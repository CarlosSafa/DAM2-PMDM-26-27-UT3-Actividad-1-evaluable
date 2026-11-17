package com.example.actividades

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Ejercicio1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        val etUrl = findViewById<EditText>(R.id.etUrl)
        val btnAbrirUrl = findViewById<Button>(R.id.btnAbrirUrl)

        btnAbrirUrl.setOnClickListener {
            val textoUrl = etUrl.text.toString().trim()

            if (textoUrl.isEmpty() || !Patterns.WEB_URL.matcher(textoUrl).matches()) {
                Toast.makeText(
                    this,
                    "Por favor, introduce una URL válida",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val urlCompleta = if (textoUrl.startsWith("http://") || textoUrl.startsWith("https://")) {
                    textoUrl
                } else {
                    "https://$textoUrl"
                }

                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlCompleta))
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        this,
                        "No se encontró ninguna aplicación para abrir la URL",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}