package com.microdesarrollo.alex_aguero_20240624

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class BitcoinValueAdapter(private val context: Context, private val dataSource: List<BitcoinValue>) : RecyclerView.Adapter<BitcoinValueAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fechaTextView: TextView = view.findViewById(R.id.tvFecha)
        val valorTextView: TextView = view.findViewById(R.id.tvValor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_bitcoin_value, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitcoinValue = dataSource[position]
        holder.fechaTextView.text = bitcoinValue.fecha
        holder.valorTextView.text = bitcoinValue.valor.toString()
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}
