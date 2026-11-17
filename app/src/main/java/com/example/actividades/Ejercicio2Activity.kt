package com.example.actividades

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Ejercicio2Activity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private var runnableNavegacion: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        val btnNavegar = findViewById<Button>(R.id.btnNavegarRetrasado)
        val tvEstado = findViewById<TextView>(R.id.tvEstadoEj2)

        btnNavegar.setOnClickListener {
            btnNavegar.isEnabled = false
            tvEstado.text = "Navegando en 10 segundos mediante PendingIntent..."
            Toast.makeText(this, "Temporizador de 10 segundos iniciado", Toast.LENGTH_SHORT).show()

            val intentDestino = Intent(this, Ejercicio2DestinoActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intentDestino,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            runnableNavegacion = Runnable {
                try {
                    pendingIntent.send()
                } catch (e: PendingIntent.CanceledException) {
                    e.printStackTrace()
                }
            }

            handler.postDelayed(runnableNavegacion!!, 10_000L)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        runnableNavegacion?.let { handler.removeCallbacks(it) }
    }
}