package com.example.actividades

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Ejercicio3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        findViewById<Button>(R.id.btnSub1).setOnClickListener {
            startActivity(Intent(this, Ejercicio3Sub1Activity::class.java))
        }
        findViewById<Button>(R.id.btnSub2).setOnClickListener {
            startActivity(Intent(this, Ejercicio3Sub2Activity::class.java))
        }
        findViewById<Button>(R.id.btnSub3).setOnClickListener {
            startActivity(Intent(this, Ejercicio3Sub3Activity::class.java))
        }
    }
}