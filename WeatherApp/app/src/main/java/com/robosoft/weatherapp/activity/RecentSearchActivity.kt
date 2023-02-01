package com.robosoft.weatherapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.robosoft.weatherapp.R

class RecentSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_search)

        val backButton = findViewById<ConstraintLayout>(R.id.include)
        backButton.findViewById<ImageView>(R.id.back_iv).setOnClickListener {
            onBackPressed()
        }
        val delete = findViewById<TextView>(R.id.clear_tv)
        delete.setOnClickListener {
            onClearPressed()
        }
        val search = findViewById<ImageView>(R.id.search_iv)
        search.setOnClickListener {
            val intent = Intent(this, CustomSearchScreen::class.java)
            startActivity(intent)
        }
    }

    private fun onClearPressed() {
        MaterialAlertDialogBuilder(this)
            .setMessage("Are you sure want to clear all the favourites?")
            .setNegativeButton("No") { _, _ ->
                finish()
            }
            .setPositiveButton("Yes") { _, _ ->
                val favouriteLayout = findViewById<ConstraintLayout>(R.id.favourite_layout)
                val emptyLayout = findViewById<RelativeLayout>(R.id.empty_layout)
                favouriteLayout.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE
            }
            .show()
    }
}