package com.ua.hlibkorobov.blood_pressure_bpm_tracker

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)

        val previous: MaterialToolbar = findViewById(R.id.topAppBar)
        val itemList: RecyclerView = findViewById(R.id.itemList)

        itemList.layoutManager = LinearLayoutManager(this)
        itemList.adapter = ItemsAdapter(MainActivity.items, this, MainActivity.items.size)

        previous.setNavigationOnClickListener {
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}