package rg.info.storagelocator.data

import android.content.Context
import com.google.gson.Gson
import rg.info.storagelocator.data.model.Container
import java.util.UUID

object Containers {
    private var containers: List<Container> = emptyList()

    init {
        deleteAllContainers()
        generateRandomContainers()
    }

    // adds or updates a container
    fun saveContainer(container: Container, context: Context) {
        //
        if (this.containers.any { it.getUUID() == container.getUUID() }) {
            this.updateContainer(container)
            this.storeContainers(context)
            return
        }
        this.containers = this.containers.plus(container)
        this.storeContainers(context)
    }

    private fun updateContainer(container: Container) {
        this.containers.first { it.getUUID() == container.getUUID() }.name = container.name
        this.containers.first { it.getUUID() == container.getUUID() }.description =
            container.description
        this.containers.first { it.getUUID() == container.getUUID() }.location =
            container.location

    }

    // removes first container with the specified name else, do nothing
    fun removeContainer(uuid: UUID, context: Context) {
        if (this.containers.any { it.getUUID() == uuid }) {
            this.containers = this.containers.minus(this.containers.first { it.getUUID() == uuid })
            this.storeContainers(context)
        }
    }

    fun getContainers(): List<Container> {
        return this.containers
    }

    // Function that returns a container by its UUID, or null
    fun getContainer(uuid: UUID): Container? {
        return this.containers.firstOrNull { it.getUUID() == uuid }
    }

    // Function that returns a random UUID that is not used by any container
    fun getRandomUUID(): UUID {
        val uuid = UUID.randomUUID()

        return if (this.containers.any { it.getUUID() == uuid }) {
            getRandomUUID()
        } else {
            uuid
        }
    }

    // Function that stores the list of containers in the shared preferences
    private fun storeContainers(context: Context) {
        val jsonString = Gson().toJson(this.containers)
        val sharedPref = context.getSharedPreferences(
            "storage-locator", Context.MODE_PRIVATE
        )
        val editor = sharedPref.edit()
        editor.putString("containers", jsonString)
        editor.apply()
    }

    // Function that loads the list of containers from the shared preferences
    fun loadContainers(context: Context) {
        val sharedPref = context.getSharedPreferences(
            "storage-locator", Context.MODE_PRIVATE
        )
        // can be null if there is no containers stored
        val jsonString = sharedPref.getString("containers", null) ?: return
        val containers = Gson().fromJson(jsonString, Array<Container>::class.java).toList()
        this.containers = containers
    }

    fun deleteAllContainers() {
        this.containers = emptyList()
    }

    // Function that generates a list of containers for testing purposes
    private fun generateRandomContainers() {
        for (i in 1..100) {
            this.containers = this.containers.plus(
                Container(
                    "Conteneur $i",
                    "Description $i",
                    "Location $i",
                    this.getRandomUUID()
                )
            )
            for (j in 1..5) {
                this.containers[i - 1].addItem("Item $j")
            }
        }
    }
}