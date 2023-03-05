package rg.info.storagelocator.data.model

import java.util.UUID

class Container(
    var name: String,
    var description: String,
    var location: String,
    private var uuid: UUID = UUID.randomUUID()
    // 2 constructors are created, one with the UUID and one without
) {
    private var items: List<String> = listOf()

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