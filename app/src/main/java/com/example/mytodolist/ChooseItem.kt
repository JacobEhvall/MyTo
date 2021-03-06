package com.example.mytodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.DescriptorProtos
import com.google.type.LatLng
import kotlinx.android.synthetic.main.activity_choose_item.*

class ChooseItem : AppCompatActivity() {

    // Tar in / deklarerar database och textfält.
    lateinit var db : FirebaseFirestore
    lateinit var  storeTextView : EditText
    lateinit var itemTextView : EditText
    private lateinit var auth: FirebaseAuth
    var lat : Double? = null
    var lng : Double? = null


    // Tar in Firebase och  auth objekt. Tar in  rätt på inputfällt  och letar reda på dess olika id:en.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_item)

        storeTextView = findViewById(R.id.edit_store)
        itemTextView = findViewById(R.id.edit_item)
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        // Skapar en save - knapp och letar efter det satta id:et.
        val saveButton = findViewById<Button>(R.id.save_to_list)

        //Sätter en listener, på save knappen och kallar på funktionen addNewItem som  vi kallar på och lägger till en vara.
        save_to_list.setOnClickListener { view ->
            addNewItem()
        }

        // Skapar en variabel för en knapp och sätter ett id som vi letar upp
        // och sätter en listener. Därefter skapar vi ett intent för att gå vidare till en ny aktivitet i detta fall MapsToDo.
        val pinOnMap = findViewById<Button>(R.id.pin_on_map)
        pinOnMap.setOnClickListener { view ->
            val intent = Intent(this, MapsToDo::class.java)
            startActivityForResult(intent,1)
        }

    } // funktion som lägger till en ny vara och kollar att inputfällten inte är tomma.
    // Skapar variablar för id:ena och ser till att de är strängar vi tar in.
    private fun addNewItem() {

        val user = auth.currentUser
        val getStore = storeTextView.text.toString()
        val insertItem = itemTextView.text.toString()

        if(getStore.isEmpty() || insertItem.isEmpty())
            return

        // En item tar in en store och ett item men också lat och lng där vi ska köpa vår item/vara.
        val item = Item(getStore, insertItem, lat, lng)
        val ref = db.collection("users").document(user!!.uid).collection("items").document()
        item.id = ref.id
        ref.set(item)
        //DataManager.items.add(item) // läggdes till i lista men sparas inte i databasen när appen avslutas.
        finish()
    }
    // Hämtar in longitude och latitude efter vad man har på kartan.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        lat = data?.getDoubleExtra("latitude",1.0)
        lng = data?.getDoubleExtra("longitude",1.0)


    }
}

