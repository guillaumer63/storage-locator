package rg.info.storagelocator.data.model

import java.util.UUID

class Container(
    var name: String,
    var description: String,
    var location: String,
    private var uuid: UUID
) {
    private var items: List<String> = emptyList()

    fun addItem(item: String) {
        items = items.plus(item)
    }

    fun removeItem(item: String) {
        items = items.minus(item)
    }

    fun getItems(): List<String> {
        return items
    }

    fun getUUID(): UUID {
        return uuid
    }
}