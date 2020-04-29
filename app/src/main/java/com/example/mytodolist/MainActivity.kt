package com.example.mytodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemRecyclerAdapter(this, DataManager.items)

        val fab = findViewById<View>(R.id.floatingActionButton)
        fab.setOnClickListener { view ->
            val intent = Intent(this, ChooseItem::class.java)
            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}





// 1. Gör som Davids video och se till att det fungerar!!
// 2. Gör så att om du skiver i ett fällt och det fattas på ett ska det inte gå att lägga till.