package com.robosoft.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.robosoft.weatherapp.R
import com.robosoft.weatherapp.database.WeatherData
import com.robosoft.weatherapp.database.DatabaseWeather
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecyclerAdapter(val data: List<WeatherData>, val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val database = DatabaseWeather.getDatabase(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val location = itemView.findViewById<TextView>(R.id.district_state_tv)
        val likeBtn = itemView.findViewById<MaterialCheckBox>(R.id.fav_iv)
        val temperature = itemView.findViewById<TextView>(R.id.temp_tv)
        val description = itemView.findViewById<TextView>(R.id.text_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.location.text = data[position].location
        holder.temperature.text = data[position].temperature.toString()
        holder.description.text = data[position].desc

//        if (data[position].isFavourite) {
//            holder.likeBtn.checkedState = ImageView.STATE_CHECKED
//        } else {
//            holder.likeBtn.checkedState = ImageView.STATE_UNCHECKED
//        }
//        holder.likeBtn.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                if (holder.likeBtn.checkedState == ImageView.STATE_CHECKED) {
//                    GlobalScope.launch {
//                        database.weatherDao().removeFavourite(data[holder.adapterPosition].id!!)
//                    }
//                } else {
//                    GlobalScope.launch {
//                        database.weatherDao().setFavourite(data[holder.adapterPosition].id!!)
//                    }
//                }
//            }
//        })
    }
}