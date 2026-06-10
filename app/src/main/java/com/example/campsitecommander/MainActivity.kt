package com.example.campsitecommander

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var etGearName: EditText
    private lateinit var etCategory: EditText
    private lateinit var etQuantity: EditText
    private lateinit var etComments: EditText
    private lateinit var btnAddGear: Button
    private lateinit var btnViewPacked: Button
    private lateinit var tvGearCount: TextView
    private lateinit var gearList: ArrayList<String>
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "CampsitePrefs"
    private val GEAR_LIST_KEY = "gear_list_key"
    private val DETAIL_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link views
        etGearName = findViewById(R.id.etGearName)
        etCategory = findViewById(R.id.etCategory)
        etQuantity = findViewById(R.id.etQuantity)
        etComments = findViewById(R.id.etComments)
        btnAddGear = findViewById(R.id.btnAddGear)
        btnViewPacked = findViewById(R.id.btnViewPacked)
        tvGearCount = findViewById(R.id.tvGearCount)

        // Setup SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        loadGearList()

        // Add gear button
        btnAddGear.setOnClickListener {
            val name = etGearName.text.toString()
            val category = etCategory.text.toString()
            val quantity = etQuantity.text.toString()
            val comments = etComments.text.toString()

            if (name.isNotEmpty() && category.isNotEmpty() && quantity.isNotEmpty()) {
                val gearItem = "Name: $name | Category: $category | Qty: $quantity | Notes: $comments"
                gearList.add(gearItem)
                saveGearList()
                updateGearCount()
                clearInputs()
            }
        }

        // View packed list button
        btnViewPacked.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putStringArrayListExtra("GEAR_LIST", gearList)
            startActivityForResult(intent, DETAIL_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()
        loadGearList()
        updateGearCount()
    }

    private fun saveGearList() {
        val gson = Gson()
        val json = gson.toJson(gearList)
        sharedPreferences.edit().putString(GEAR_LIST_KEY, json).apply()
    }

    private fun loadGearList() {
        val gson = Gson()
        val json = sharedPreferences.getString(GEAR_LIST_KEY, null)
        val type = object : TypeToken<ArrayList<String>>() {}.type
        gearList = if (json != null) gson.fromJson(json, type) else ArrayList()
    }

    private fun updateGearCount() {
        tvGearCount.text = "Total Items Packed: ${gearList.size}"
    }

    private fun clearInputs() {
        etGearName.text.clear()
        etCategory.text.clear()
        etQuantity.text.clear()
        etComments.text.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DETAIL_REQUEST_CODE && resultCode == RESULT_OK) {
            val updatedList = data?.getStringArrayListExtra("UPDATED_GEAR_LIST")
            if (updatedList != null) {
                gearList = updatedList
                updateGearCount()
                saveGearList()
            }
        }
    }
}