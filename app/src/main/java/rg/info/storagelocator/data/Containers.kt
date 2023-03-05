package rg.info.storagelocator.data

import android.content.Context
import com.google.gson.Gson
import rg.info.storagelocator.data.model.Container
import java.util.UUID

class Containers(context: Context) {
    private var containers: List<Container> = listOf()

    // at instantiation
    init {
        // load the containers from the shared preferences
        this.loadContainers(context)
        this.deleteAllContainers()
        this.generateRandomContainers()
        this.storeContainers(context)
    }

    // checks if the container with the same name or UUID already exists
    fun addContainer(container: Container, context: Context) {
        // if the container with the same name or UUID already exists, throw an exception
        if (containers.any() { it.name == container.name })
            throw Exception("Container with name ${container.name} already exists")
        else if (containers.any() { it.getUUID() == container.getUUID() })
            throw Exception("Container with UUID ${container.getUUID()} already exists")

        containers = containers.plus(container)
        this.storeContainers(context)

        // todo: add a check if the container with the same name or UUID already exists in the activity
        // and display a toast message
    }

    // removes first container with the specified name
    fun removeContainer(containerName: String, context: Context) {
        containers = containers.minus(containers.first() { it.name == containerName })
        this.storeContainers(context)
    }

    fun getContainers(): List<Container> {
        return containers
    }

    // Function that returns a container by its UUID, or null
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

    // Function that loads the list of containers from the shared preferences
    private fun loadContainers(context: Context) {
        val sharedPref = context.getSharedPreferences(
            "storage-locator", Context.MODE_PRIVATE
        )
        // can be null if there is no containers stored
        val jsonString = sharedPref.getString("containers", null) ?: return
        val containers = Gson().fromJson(jsonString, Array<Container>::class.java).toList()
        this.containers = containers
    }

    fun deleteAllContainers() {
        containers = listOf()
    }

    // Function that generates a list of containers for testing purposes
    private fun generateRandomContainers() {
        for (i in 1..26) {
            containers = containers.plus(
                Container(
                    "Conteneur $i",
                    "Description $i",
                    "Location $i"
                )
            )
            for (j in 1..5) {
                containers[i - 1].addItem("Item $j")
            }
        }
    }
}