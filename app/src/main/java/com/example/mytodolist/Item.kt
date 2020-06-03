package com.example.mytodolist
// Deklarera variabler och dess typ.
data class Item(var store : String? = null,
                var item : String? = null,
                var lat : Double? = null,
                var lng : Double? = null,
                var id : String? = null
)
