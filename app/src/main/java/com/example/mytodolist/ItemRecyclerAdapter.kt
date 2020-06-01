package com.example.mytodolist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.student_list_view.*

class ItemRecyclerAdapter(private val context: Context, private val items: List<Item>) :
    RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>() {
    private val layoutInflator = LayoutInflater.from(context)
    private lateinit var auth: FirebaseAuth
    var latLng: LatLng? = null




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

        val user = auth.currentUser

        val item = items[position]
        db.collection("users").document(user!!.uid).collection("items").document(user.uid).delete()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val textstoreName = itemView.findViewById<TextView>(R.id.text_storeName)
        val delete = itemView.findViewById<ImageButton>(R.id.delete)
        var itemPosition = 0

        init {

            // Denna gör så att om du klickar på på en recyclerview field kommer du till en annan.
            // Hit ska vi länka så att när användaren klickar på vy kommeer användaren till maps/kartan.

            itemView.setOnClickListener {
                val intent = Intent(context, WatchPin::class.java)
                // Här ska kordinaterna skickas med till aktiviteten. För att göra det
                // använd putextra!
                var onMap = items[itemPosition]
                intent.putExtra("latitude",onMap.lat)
                intent.putExtra("longitude",onMap.lng)
                context.startActivity(intent)

            }
            delete.setOnClickListener { view ->
                removeItem(itemPosition)
                Snackbar.make(view, "Item Removed", Snackbar.LENGTH_SHORT).show()

            }

        }
    }
}

//1. skapa en ny maps activity KLAR
//2. rad 60 och 61 ska du starat den nya mapsaktivitetten i stället KLAR
//3. skicka med latitude och longitude frpn denna adapter till den nya aktiviteten
//          använd putExtra för att skicak med KLAR
//4. i din nya maps activity ska du ta emot lat och lng och göra en println
