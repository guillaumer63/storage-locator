package rg.info.storagelocator.data

import android.content.Context
import com.google.gson.Gson
import java.util.UUID

class Container(
    val name: String,
    val description: String,
    val location: String,
    val uuid: UUID = UUID.randomUUID()
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

class Containers(context: Context) {
    private var containers: List<Container> = listOf()

    init {
        // load the containers from the shared preferences
        loadContainers(context)
    }

    // checks if the container with the same name or UUID already exists
    fun addContainer(container: Container, context: Context) {
        if (containers.any() { it.name == container.name })
            throw Exception("Container with name ${container.name} already exists")
        else if (containers.any() { it.getUUID() == container.getUUID() })
            throw Exception("Container with UUID ${container.getUUID()} already exists")

        containers = containers.plus(container)
        storeContainers(context)
    }

    fun removeContainer(containerName: String, context: Context) {
        containers = containers.minus(containers.first() { it.name == containerName })
        storeContainers(context)
    }

    fun getContainers(): List<Container> {
        return containers
    }

    // Function that returns a container by its UUID
    fun getContainer(uuid: UUID): Container? {
        return containers.firstOrNull() { it.getUUID() == uuid }
    }

    // Function that stores the list of containers in the shared preferences
    private fun storeContainers(context: Context) {
        val jsonString = Gson().toJson(containers)
        val sharedPref = context.getSharedPreferences(
            "storage-locator", Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putString("containers", jsonString)
        editor.apply()
    }

    private fun loadContainers(context: Context) {
        val sharedPref = context.getSharedPreferences(
            "storage-locator", Context.MODE_PRIVATE
        )
        // can be null if there is no containers stored
        val jsonString = sharedPref.getString("containers", null) ?: return
        val containers = Gson().fromJson(jsonString, Array<Container>::class.java).toList()
        this.containers = containers
    }
}