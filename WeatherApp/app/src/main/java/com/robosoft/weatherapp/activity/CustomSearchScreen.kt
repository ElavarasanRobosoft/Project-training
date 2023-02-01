package com.robosoft.weatherapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.robosoft.weatherapp.R
import kotlinx.android.synthetic.main.custom_searchbar.*

class CustomSearchScreen : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_search_screen)

        val backButton = findViewById<ConstraintLayout>(R.id.include_search)
        backButton.findViewById<ImageView>(R.id.back_iv).setOnClickListener {
            onBackPressed()
        }
        val clearButton = findViewById<ConstraintLayout>(R.id.include_search)
        clearButton.findViewById<ImageView>(R.id.close_iv).setOnClickListener {
            clearButton.findViewById<EditText>(R.id.search_et).text.clear()
        }
        val search = findViewById<Button>(R.id.search)
        search.setOnClickListener {
             val cityValue = search_et.text.toString()
            val intent = Intent()
            intent.putExtra("city",cityValue)
            setResult(100,intent)
            finish()
        }
    }
}