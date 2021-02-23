package com.example.marathimatrimony
import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HobbiesAndInterest: AppCompatActivity() {

    lateinit var tvDay:TextView
    lateinit var selectedDay:BooleanArray
    var dayList:ArrayList<Int> = ArrayList()
    private var dayArray = arrayOf("Sunday","monday","Tuesday","wednesday","Thursday","friday","saturday","Sunday","monday","Tuesday","wednesday","Thursday","friday","saturday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobbies_and_interest)

        tvDay=findViewById(R.id.editHobbies)

        selectedDay = BooleanArray(dayArray.size)
        tvDay.setOnClickListener {
            val builder = AlertDialog.Builder(this@HobbiesAndInterest)
            builder.setTitle("Select")
                    .setCancelable(false)
            builder.setMultiChoiceItems(dayArray, selectedDay) { _, i, b ->
                if (b) {
                    dayList.add(i)
                } else {
                    dayList.remove(i)
                }
            }
            builder.setPositiveButton("ok") { _, _ ->
                val stringBuilder = StringBuilder()
                for (j in 0 until dayList.size)
                {
                    stringBuilder.append(dayArray[dayList[j]])

                    if (j in -1 until  dayList.size)
                    {
                        stringBuilder.append(",")
                    }
                }
                tvDay.text = stringBuilder.toString()
            }
            builder.setNegativeButton("clear All") { _, _ ->

                for (j in 0 until dayList.size)
                {
                    selectedDay[j]=false

                    dayList.clear();

                    tvDay.text = ""
                }

            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}