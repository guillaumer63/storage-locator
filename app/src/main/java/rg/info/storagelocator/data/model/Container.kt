package rg.info.storagelocator.data.model

import java.util.UUID

class Container(
    var name: String,
    var description: String,
    var location: String,
    private var uuid: UUID
) {
    private var items: List<String> = emptyList()

    // cannot add the same item twice
    fun addItem(item: String) {
        if (items.contains(item)) return

        items = items.plus(item)
    }

    fun removeItem(item: String) {
        items = items.minus(item)
    }

    // function to update an item
    // the list is still in the same order
    fun updateItem(oldItem: String, newItem: String) {
        val index = items.indexOf(oldItem)
        // replace the old item with the new one
        items = items.toMutableList().apply { set(index, newItem) }
    }

    fun getItems(): List<String> {
        return items
    }

    fun getUUID(): UUID {
        return uuid
    }
}