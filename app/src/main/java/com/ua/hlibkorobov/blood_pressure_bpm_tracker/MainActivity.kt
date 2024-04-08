package com.ua.hlibkorobov.blood_pressure_bpm_tracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        val items = mutableListOf<PressureRecord>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val buttonAdd: FloatingActionButton = findViewById(R.id.fab)
        val buttonHistory: ExtendedFloatingActionButton = findViewById(R.id.efab)
        val itemList: RecyclerView = findViewById(R.id.itemList)

        itemList.layoutManager = LinearLayoutManager(this)
        itemList.adapter = ItemsAdapter(items, this, 3)

        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddRecord::class.java)
            startActivity(intent)
        }

        buttonHistory.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }
    }
}
