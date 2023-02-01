package com.robosoft.weatherapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.robosoft.weatherapp.R
import com.robosoft.weatherapp.ServiceBuilderObject
import com.robosoft.weatherapp.database.DatabaseWeather
import com.robosoft.weatherapp.database.WeatherData
import com.robosoft.weatherapp.databinding.HomeActivityBinding
import com.robosoft.weatherapp.model.ApiResult
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var database: DatabaseWeather
    var flag = false

    var city = "chennai"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()

        database = DatabaseWeather.getDatabase((this@HomeActivity.applicationContext ?: this))

        val dateFormat = SimpleDateFormat("E,dd MMM yyyy")
        val currentDate = dateFormat.format(Date())
        binding.dateTv.text = currentDate.toString()
        val favIcon: ImageView = binding.favouriteIv

        favIcon.setOnClickListener {
            if (!flag) {
                binding.favouriteIv.setImageResource(R.drawable.icon_favourite_selected)
                Toast.makeText(this, "Added to favourite", Toast.LENGTH_SHORT).show()
                flag = true
            } else {
                binding.favouriteIv.setImageResource(R.drawable.icon_favourite)
                Toast.makeText(this, "Removed from favourite", Toast.LENGTH_SHORT).show()
                flag = false
            }
        }



        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setSupportActionBar(home_toolbar)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.favourite -> startActivity(
                    Intent(
                        applicationContext,
                        FavouriteActivity::class.java
                    )
                )
                R.id.recent_search -> startActivity(
                    Intent(
                        applicationContext,
                        RecentSearchActivity::class.java
                    )
                )
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        R.id.search_iv
        if (item.itemId == R.id.search_iv) {
            val intent = Intent(this, CustomSearchScreen::class.java)
            startActivityForResult(intent,100)
        } else {
            toggle.onOptionsItemSelected(item)
            return true
        }
        return false
    }

    private fun getData() {
        GlobalScope.launch {
            val data = ServiceBuilderObject.reterofitService()?.getData(city)
            data?.enqueue(object : retrofit2.Callback<ApiResult> {
                override fun onResponse(
                    call: Call<ApiResult>,
                    response: Response<ApiResult>
                ) {
                    if (response.body() != null) {
                        if (city != "chennai") {
                            val savingData = WeatherData(
                                null,
                                city,
                                response.body()?.main!!.temp,
                                response.body()!!.weather[0].description,
                                isFavourite = false
                            )
                            GlobalScope.launch {
                                database.weatherDao().insertData(savingData)
                            }
                        }
                        binding.temperatureTv.text =
                            response.body()?.main?.temp.toString()
                        binding.placeTv.text = response.body()?.name.toString()
                        binding.temperatureNameTv.text =
                            response.body()!!.weather[0].description

                        binding.minMaxTv?.text =
                            "${response.body()!!.main?.temp_min?.toInt()} - ${response.body()!!.main?.temp_max?.toInt()}"
                        binding.precipitationTv.text =
                            response.body()!!.wind.speed.toString()

                        binding.humidityTv.text =
                            response.body()!!.main.humidity.toString()
                    } else {
                        Toast.makeText(this@HomeActivity, "City Not Found", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                override fun onFailure(
                    call: Call<ApiResult>,
                    t: Throwable
                ) {
                    Log.d("Hello", "Error")
                }
            })
        }
    }
}