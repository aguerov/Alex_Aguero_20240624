package com.microdesarrollo.alex_aguero_20240624

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class BitcoinValuesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitcoin_values)

        val recyclerView: RecyclerView = findViewById(R.id.rvBitcoinValues)
        recyclerView.layoutManager = LinearLayoutManager(this)

        FetchBitcoinValuesTask(recyclerView).execute("https://mindicador.cl/api/bitcoin")

        val backButton: Button = findViewById(R.id.btnBack)
        backButton.setOnClickListener {
            finish()
        }
    }

    inner class FetchBitcoinValuesTask(private val recyclerView: RecyclerView) : AsyncTask<String, Void, List<BitcoinValue>>() {
        override fun doInBackground(vararg urls: String): List<BitcoinValue>? {
            val url = URL(urls[0])
            val urlConnection = url.openConnection() as HttpURLConnection
            return try {
                val inputStream = urlConnection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                val result = reader.readText()
                reader.close()
                parseJson(result)
            } finally {
                urlConnection.disconnect()
            }
        }

        override fun onPostExecute(result: List<BitcoinValue>?) {
            super.onPostExecute(result)
            if (result != null) {
                val adapter = BitcoinValueAdapter(this@BitcoinValuesActivity, result)
                recyclerView.adapter = adapter
            }
        }

        private fun parseJson(json: String): List<BitcoinValue> {
            val bitcoinValues = mutableListOf<BitcoinValue>()
            val jsonObject = JSONObject(json)
            val seriesArray: JSONArray = jsonObject.getJSONArray("serie")
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            for (i in 0 until seriesArray.length()) {
                val item = seriesArray.getJSONObject(i)
                val fecha = item.getString("fecha")
                val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(fecha)
                val formattedDate = dateFormat.format(date)
                val valor = item.getDouble("valor")
                bitcoinValues.add(BitcoinValue(formattedDate, valor))
            }
            return bitcoinValues
        }
    }
}
