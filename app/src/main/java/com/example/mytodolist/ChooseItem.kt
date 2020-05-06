package com.example.mytodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_choose_item.*
import kotlinx.android.synthetic.main.student_list_view.*

class ChooseItem : AppCompatActivity() {

    lateinit var  storeTextView : EditText
    lateinit var itemTextView : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_item)

        storeTextView = findViewById(R.id.edit_store)
        itemTextView = findViewById(R.id.edit_item)

        val saveButton = findViewById<Button>(R.id.btn_save)

        btn_save.setOnClickListener { view ->
            addNewItem()
        }

        val deleteButton = findViewById<ImageButton>(R.id.delete)
    }
    fun addNewItem() {

        val db = FirebaseFirestore.getInstance()


        val getStore = storeTextView.text.toString()
        val insertItem = itemTextView.text.toString()

        val item = Item("items")
        db.collection("items").add(item)
        DataManager.items.add(item)
        finish()
    }

}





