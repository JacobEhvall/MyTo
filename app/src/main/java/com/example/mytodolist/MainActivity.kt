package com.example.mytodolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class MainActivity : AppCompatActivity() {

    lateinit var  storeTextView : EditText
    lateinit var itemTextView : EditText
    lateinit var buttonSave : Button

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        val shoppingItems = mutableListOf<Item>()

        db.collection("items")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val newItem = document.toObject(Item::class.java)
                        if (newItem != null) {
                            shoppingItems.add(newItem)
                        }
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Log.w(
                        "hej",
                        "Error getting documents.",
                        task.exception
                    )
                }
            })



        recyclerView = findViewById<RecyclerView>(R.id.studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemRecyclerAdapter(this, shoppingItems)

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


// 1. Saker läggs till men måste nu sparas i databasen!
// 2. Item ska inte vara klickbar!
// Kolla på video som finns där David lägger in lök!!!

// läsa vad som finns ifrån Firestore