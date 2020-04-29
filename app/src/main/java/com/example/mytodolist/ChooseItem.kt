package com.example.mytodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_choose_item.*

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
    }
    fun addNewItem() {
        val getStore = storeTextView.text.toString()
        val insertItem = itemTextView.text.toString()

        val item = Item(getStore, insertItem)
        DataManager.items.add(item)
        finish()
    }
}


// 2:53 samma VIDEO 2 timmar och 53 minuter

