package com.example.calculateyourageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerBtn = findViewById<Button>(R.id.datePickerBtn)

        datePickerBtn.setOnClickListener {
            view->
            onClickOfDatePicker(view)
        }
    }

    fun onClickOfDatePicker(view:View)
    {
        val slcDateView=findViewById<TextView>(R.id.tvSelectedDate)
        val slcDateViewInMinute=findViewById<TextView>(R.id.tvSelectedDateInMinute)
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view,SelectedYear,SelectedMonth,SelectedDayOfMonth ->

            val selectedDate="$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
            slcDateView.setText(selectedDate)

            val sdf=SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            val selectedDateInMinute=theDate!!.time/60000

            val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinute=currentDate!!.time/60000

            val difInMinute=currentDateInMinute-selectedDateInMinute

            slcDateViewInMinute.setText(difInMinute.toString())


        }
            ,year
            ,month
            ,day
        )

        dpd.datePicker.setMaxDate(Date().time-8600000)
        dpd.show()
    }
}