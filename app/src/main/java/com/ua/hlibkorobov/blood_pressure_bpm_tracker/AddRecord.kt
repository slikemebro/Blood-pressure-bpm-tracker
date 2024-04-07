package com.ua.hlibkorobov.blood_pressure_bpm_tracker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddRecord : AppCompatActivity() {

    private lateinit var timePickerButton: ExtendedFloatingActionButton
    private lateinit var datePickerButton: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_record)

        val previous: MaterialToolbar = findViewById(R.id.topAppBar)
        val systolicPicker: NumberPicker = findViewById(R.id.systolicPicker)
        val diastolicPicker: NumberPicker = findViewById(R.id.diastolicPicker)
        val pulsePicker: NumberPicker = findViewById(R.id.pulsePicker)
        val saveButton: Button = findViewById(R.id.saveButton)
        datePickerButton = findViewById(R.id.datePickerButton)
        timePickerButton = findViewById(R.id.timePickerButton)

        previous.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        systolicPicker.setOnValueChangedListener { picker, _, newVal ->
            picker.value = newVal
        }

        timePickerButton.setOnClickListener {
            showTimePickerDialog()
        }

        datePickerButton.setOnClickListener {
            showDatePickerDialog()
        }

        saveButton.setOnClickListener {
            val dateTime = getDate()

            val pressureRecord = PressureRecord(
                systolicPicker.value,
                diastolicPicker.value,
                pulsePicker.value,
                dateTime
            )
            MainActivity.addElements(pressureRecord)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setNumberPicker(systolicPicker, 100, 200)
        setNumberPicker(diastolicPicker, 78, 200)
        setNumberPicker(pulsePicker, 80, 300)
        setCurrentDate()
        setCurrentTime()
    }

    private fun getDate(): Date? {
        val dateTimeString = "${datePickerButton.text} ${timePickerButton.text}"
        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return dateTimeFormat.parse(dateTimeString)
    }

    private fun setNumberPicker(numberPicker: NumberPicker, defaultValue: Int, maxValue: Int) {
        numberPicker.minValue = 0
        numberPicker.maxValue = maxValue
        numberPicker.wrapSelectorWheel = false
        numberPicker.value = defaultValue
    }

    private fun setCurrentTime() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY).toString()
        val minute = if (currentTime.get(Calendar.MINUTE) < 10) {
            "0" + currentTime.get(Calendar.MINUTE).toString()
        } else {
            currentTime.get(Calendar.MINUTE).toString()
        }
        val timeFormat = getString(R.string.time_format)
        val timeText = String.format(Locale.getDefault(), timeFormat, hour, minute)
        timePickerButton.text = timeText
    }

    private fun setCurrentDate() {
        datePickerButton.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }

    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val formattedMinutes = if (selectedMinute < 10) {
                    "0${selectedMinute}"
                } else {
                    selectedMinute.toString()
                }
                val selectedTime = "$selectedHour:$formattedMinutes"
                timePickerButton.text = selectedTime
            },
            hour,
            minute,
            false
        )

        timePickerDialog.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedMonth = if (selectedMonth + 1 < 10) {
                    "0${selectedMonth + 1}"
                } else {
                    (selectedMonth + 1).toString()
                }
                val selectedDate = "$selectedDay/$formattedMonth/$selectedYear"
                datePickerButton.text = selectedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}