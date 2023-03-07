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
        Containers.deleteAllContainers()
        val context: Context = ApplicationProvider.getApplicationContext()
        val container1 = Container(
            "test",
            "desc",
            "loc",
            Containers.getRandomUUID()
        )


        assert(container1.description == "desc")
        assert(container1.location == "loc")

        Containers.addContainer(container1, context)
        assert(Containers.getContainers().size == 1)

        Containers.getContainers()[0].addItem("pen")
        assert(Containers.getContainers()[0].getItems().size == 1)

        Containers.getContainer(container1.getUUID())?.removeItem("pen")
        assert(Containers.getContainers()[0].getItems().isEmpty())

        val uuid = UUID.randomUUID()
        val container2 = Container("test2", "test2", "test2", uuid)
        Containers.addContainer(container2, context)

        // Elvis operator: if the left side is not null, return it, otherwise return the right side
        assert((Containers.getContainer(uuid)?.getUUID() ?: UUID.randomUUID()) == uuid)

        Containers.removeContainer("test", context)
        Containers.removeContainer("test2", context)
        assert(Containers.getContainers().isEmpty())
        Containers.deleteAllContainers()
    }
}