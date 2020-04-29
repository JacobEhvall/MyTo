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
        DataManager.items.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val textstoreName = itemView.findViewById<TextView>(R.id.text_storeName)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.delete)
        var itemPosition = 0

        init {
            itemView.setOnClickListener {
                    val intent = Intent(context, ChooseItem::class.java)
                context.startActivity(intent)

            }
            deleteButton.setOnClickListener { view ->
                removeItem(itemPosition)
                Snackbar.make(view, "Item Removed", Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}


// Kolla upp var STUDENT_POSITION tas in någonstans!!