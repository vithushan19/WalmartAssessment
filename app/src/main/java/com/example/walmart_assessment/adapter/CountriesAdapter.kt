package com.example.walmart_assessment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart_assessment.R
import com.example.walmart_assessment.data.Country

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val capitalTextView: TextView = view.findViewById(R.id.capitalTextView)
        val codeTextView: TextView = view.findViewById(R.id.codeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        val nameAndRegionText =
            StringBuilder().append(country.name).append(", ").append(country.region).toString()
        holder.nameTextView.text = nameAndRegionText
        holder.capitalTextView.text = country.capital
        holder.codeTextView.text = country.code
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}
