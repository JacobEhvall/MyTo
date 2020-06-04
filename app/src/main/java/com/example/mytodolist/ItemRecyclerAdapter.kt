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

    // Tre override funktioner som behövs för att kunna ärva av Recyclerview.
    // onCreateViewHolder när vi skapar en View, GetItemCount: returnerar hur lång listan är, Binder ihop datan i view:n.
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

    } // funktion som tar bort en item, vilket är beroende på en position och kopplar ihop det med Firebase.
    fun removeItem(position: Int) {
        auth = FirebaseAuth.getInstance()
        
        val db = FirebaseFirestore.getInstance()

        val user = auth.currentUser

        val item = items[position]
        //println("!! ${item.id}")
        db.collection("users").document(user!!.uid).collection("items").document(item.id!!).delete()

    }
    // Class som endast kommer användas i denna class och inte finnas tillgänglig någon annanstans.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val textstoreName = itemView.findViewById<TextView>(R.id.text_storeName)
        val delete = itemView.findViewById<ImageButton>(R.id.delete)
        var itemPosition = 0

        init {

            // Denna gör så att om du klickar på på en recyclerview field kommer du till en annan.
            // Hit ska vi länka så att när användaren klickar på vy kommer användaren till maps/kartan.


            // Här ska också kordinaterna skickas med till aktiviteten, long och lat.För att göra det används putextra. Härifrån går vi också till en ny aktivitet WatchPin.
            itemView.setOnClickListener {
                val intent = Intent(context, WatchPin::class.java)
                var onMap = items[itemPosition]
                intent.putExtra("latitude",onMap.lat)
                intent.putExtra("longitude",onMap.lng)
                context.startActivity(intent)

            } // id:et för att ta bort en item sätts med en listener och tar bort item. En snackbar bekräftar att item:et har tagits bort.
            delete.setOnClickListener { view ->
                auth = FirebaseAuth.getInstance()
                removeItem(itemPosition)
                Snackbar.make(view, "Item Removed", Snackbar.LENGTH_SHORT).show()
                println("delete ${removeItem(itemPosition)}")

            }

        }
    }
}

