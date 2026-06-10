package com.example.campsitecommander

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerViewGear: RecyclerView
    private lateinit var btnBack: Button
    private lateinit var gearAdapter: GearAdapter
    private lateinit var gearList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        recyclerViewGear = findViewById(R.id.recyclerViewGear)
        btnBack = findViewById(R.id.btnBack)

        gearList = intent.getStringArrayListExtra("GEAR_LIST")?: ArrayList()

        gearAdapter = GearAdapter(gearList)
        recyclerViewGear.layoutManager = LinearLayoutManager(this)
        recyclerViewGear.adapter = gearAdapter

        if (gearList.isEmpty()) {
            Snackbar.make(recyclerViewGear, "No gear added yet. Go add some!", Snackbar.LENGTH_LONG).show()
        }

        btnBack.setOnClickListener {
            val resultIntent = intent
            resultIntent.putStringArrayListExtra("UPDATED_GEAR_LIST", gearList)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewGear)
    }

    private val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val deletedItem = gearList[position]

            gearList.removeAt(position)
            gearAdapter.notifyItemRemoved(position)

            Snackbar.make(recyclerViewGear, "Deleted: $deletedItem", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    gearList.add(position, deletedItem)
                    gearAdapter.notifyItemInserted(position)
                }.show()
        }
    }
}