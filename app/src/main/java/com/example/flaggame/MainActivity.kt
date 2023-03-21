package com.example.flaggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.children
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var flagArrayList: ArrayList<Flag>
    var count = 0
    var clubname = ""
    lateinit var buttonArrayList: ArrayList<Button>

    lateinit var linearBtn: LinearLayout
    lateinit var linerBtn1: LinearLayout
    lateinit var linerBtn2: LinearLayout
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonArrayList = ArrayList()

        linearBtn = findViewById(R.id.lin_1_matn)
        linerBtn1 = findViewById(R.id.lin_2_btn_1)
        linerBtn2 = findViewById(R.id.lin_3_btn_2)
        image = findViewById(R.id.image_1)

        createObject()
        btnPlaceCount()
    }

    private fun createObject() {
        flagArrayList = ArrayList()
        flagArrayList.add(Flag("germany", R.drawable.germanyq))
        flagArrayList.add(Flag("australia", R.drawable.australia))
        flagArrayList.add(Flag("belgium", R.drawable.belgium))
        flagArrayList.add(Flag("ecuador", R.drawable.ecuador))
        flagArrayList.add(Flag("england", R.drawable.england))
        flagArrayList.add(Flag("italy", R.drawable.italy))
        flagArrayList.add(Flag("kazakhstan", R.drawable.kazakhstan))
        flagArrayList.add(Flag("poland", R.drawable.poland))
        flagArrayList.add(Flag("qatar", R.drawable.qatar))
        flagArrayList.add(Flag("saudi-arabia", R.drawable.saudi_arabia))
        flagArrayList.add(Flag("south-korea", R.drawable.south_korea))
        flagArrayList.add(Flag("turkey", R.drawable.turkey))
        flagArrayList.add(Flag("uzbekistan", R.drawable.uzbekistan))
        flagArrayList.add(Flag("argentina", R.drawable.argentina))
        flagArrayList.add(Flag("portugal", R.drawable.portugal))
        flagArrayList.add(Flag("brazil", R.drawable.brazil))
        flagArrayList.add(Flag("croatia", R.drawable.croatia))
        flagArrayList.add(Flag("france", R.drawable.france))
        flagArrayList.add(Flag("spain", R.drawable.spain))
        flagArrayList.add(Flag("uruguay", R.drawable.uruguay))
        flagArrayList.add(Flag("china", R.drawable.china))
        flagArrayList.add(Flag("india", R.drawable.india))
        flagArrayList.add(Flag("russia", R.drawable.russia))
        flagArrayList.add(Flag("united-kingdom", R.drawable.united_kingdom))
        flagArrayList.add(Flag("united-states", R.drawable.united_states))
    }

    private fun btnPlaceCount() {
        image.setImageResource(flagArrayList[count].image!!)
        linearBtn.removeAllViews()
        linerBtn1.removeAllViews()
        linerBtn2.removeAllViews()
        clubname = ""
        btnPlace(flagArrayList[count].name)
    }

    private fun btnPlace(clubname: String?) {
        val btnArray: ArrayList<Button> = randomBtn(clubname)
        for (i in 0..5) {
            linerBtn1.addView(btnArray[i])
        }
        for (i in 6..11) {
            linerBtn2.addView(btnArray[i])
        }
    }

    private fun randomBtn(clubname: String?): ArrayList<Button> {
        val array = ArrayList<Button>()
        val arrayText = ArrayList<String>()

        for (c in clubname!!) {
            arrayText.add(c.toString())
        }
        if (arrayText.size != 12) {
            val str = "ABCDEFGHIJKLMNOPQRSTUVXYZ-"
            for (i in arrayText.size until 12) {
                val random = java.util.Random().nextInt(str.length)
                arrayText.add(str[random].toString())
            }
        }
        arrayText.shuffle()

        for (i in 0 until arrayText.size) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            button.text = arrayText[i]
            button.setOnClickListener(this)
            array.add(button)
        }
        return array
    }

    override fun onClick(v: View?) {
        val button1 = v as Button
        if (buttonArrayList.contains(button1)) {
            linearBtn.removeView(button1)
            var hasC = false
            linerBtn1.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    clubname = clubname.substring(0, clubname.length - 1)
                    hasC = true
                }
            }
            linerBtn2.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (!hasC) {
                        clubname = clubname.substring(0, clubname.length - 1)
                    }
                }
            }

        } else {
            button1.visibility = View.INVISIBLE
            clubname += button1.text.toString().toUpperCase(Locale.ROOT)
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f)
            button2.text = button1.text
            button2.setOnClickListener(this)
            buttonArrayList.add(button2)
            linearBtn.addView(button2)
            rightText()
        }
    }

    private fun rightText() {
        if (clubname == flagArrayList[count].name.uppercase(Locale.ROOT)) {
            Toast.makeText(this, "successful", Toast.LENGTH_SHORT).show()
            if (count == flagArrayList.size - 1) {
                count = 0
            } else {
                count++
            }
            btnPlaceCount()
        } else {
            if (clubname.length == flagArrayList[count].name?.length) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                linearBtn.removeAllViews()
                linerBtn2.removeAllViews()
                linerBtn1.removeAllViews()
                btnPlace(flagArrayList[count].name)
                clubname = ""
            }
        }
    }

}