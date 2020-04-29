package com.example.mytodolist

object DataManager {
    val items = mutableListOf<Item>()

    init {
        createThingData()
    }

    fun createThingData() {
        var item = Item("ICA", "Mjölk")
        items.add(item)
        item = Item("Åhlens", "sladd")
        items.add(item)
        item = Item("Hemköp", "grädde")
        items.add(item)
    }
}