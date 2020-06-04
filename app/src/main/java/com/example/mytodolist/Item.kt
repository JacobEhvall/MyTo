package com.example.mytodolist
// Deklarerar en konströktör vad vi tar in för variabler och dess typ, String/Double.
data class Item(var store : String? = null,
                var item : String? = null,
                var lat : Double? = null,
                var lng : Double? = null,
                var id : String? = null
)
