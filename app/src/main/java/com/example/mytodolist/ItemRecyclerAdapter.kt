package com.example.mytodolist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class ItemRecyclerAdapter(private val context: Context, private val items: List<Item>) :
    RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>() {
    private val layoutInflator = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflator.inflate(R.layout.student_list_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val getItem = items[position]
        holder.itemName.text = getItem.store
        holder.textstoreName.text = getItem.item
        holder.itemPosition = position

    }

    fun removeItem(position: Int) {

        val db = FirebaseFirestore.getInstance()

        val item = items[position]
        val docId = item.id
        if(docId != null){
            db.collection("items").document(docId).delete()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val textstoreName = itemView.findViewById<TextView>(R.id.text_storeName)
        val delete = itemView.findViewById<ImageButton>(R.id.delete)
        var itemPosition = 0

        init {

            // Denna gör så att om du klickar på på en recyclerview field kommer du till en annan.

            itemView.setOnClickListener {
                val intent = Intent(context, ChooseItem::class.java)
                context.startActivity(intent)

            }
            delete.setOnClickListener { view ->
                removeItem(itemPosition)
                Snackbar.make(view, "Item Removed", Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}


