package rg.info.storagelocator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import  org.junit.Test
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.data.model.Container
import java.util.UUID

class ContainersTest {
    @Test
    fun testContainers() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val containers = Containers(context)
        val container1 = Container("test", "desc", "loc")

        assert(container1.description == "desc")
        assert(container1.location == "loc")

        containers.addContainer(container1, context)
        assert(containers.getContainers().size == 1)

        containers.getContainers()[0].addItem("pen")
        assert(containers.getContainers()[0].getItems().size == 1)

        containers.getContainer(container1.getUUID())?.removeItem("pen")
        assert(containers.getContainers()[0].getItems().isEmpty())

        val uuid = UUID.randomUUID()
        val container2 = Container("test2", "test2", "test2", uuid)
        containers.addContainer(container2, context)

        // Elvis operator: if the left side is not null, return it, otherwise return the right side
        assert((containers.getContainer(uuid)?.getUUID() ?: UUID.randomUUID()) == uuid)

        containers.removeContainer("test", context)
        containers.removeContainer("test2", context)
        assert(containers.getContainers().isEmpty())
    }
}