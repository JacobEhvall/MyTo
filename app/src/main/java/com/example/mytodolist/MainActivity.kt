package com.example.mytodolist

import android.content.ContentProviderClient
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.Manifest
import android.content.ContentValues
import android.view.Menu
import android.view.MenuInflater


class MainActivity : AppCompatActivity() {

    lateinit var storeTextView: EditText
    lateinit var itemTextView: EditText
    lateinit var buttonSave: Button
    lateinit var recyclerView: RecyclerView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = FirebaseAuth.getInstance()

        try {
            if (auth.currentUser == null) {
                try {
                    auth.signInAnonymously()
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
// Sign in success, update UI with the signed-in user's information
                                println("User created ${auth.currentUser}")
                            } else {
// If sign in fails, display a message to the user.
                                println("User not created: ${task.exception}")
                            }
                        }.addOnFailureListener {
                            println("Error: ${it.localizedMessage}")
                        }
                } catch (e: Exception) {
                    println("Sign in error: ${e.localizedMessage}")
                }
            }
        } catch (e: Exception) {
            println("Init auth error: ${e.localizedMessage}")
        }



        val db = FirebaseFirestore.getInstance()

        val shoppingItems = mutableListOf<Item>()
        val user = auth.currentUser


            db.collection("users").document(user!!.uid).collection("items").orderBy("store")
            .addSnapshotListener { snapshot, e ->
                if (snapshot != null) {
                    shoppingItems.clear()
                    for (document in snapshot.documents) {
                        val newItem = document.toObject(Item::class.java)
                        if (newItem != null) {
                            newItem.id = document.id
                            shoppingItems.add(newItem)
                        }
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Log.w(
                        "hej",
                        "Error getting documents."
                    )
                }
            }

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














