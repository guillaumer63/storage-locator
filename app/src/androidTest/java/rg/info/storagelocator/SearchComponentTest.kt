package rg.info.storagelocator

import androidx.compose.ui.platform.LocalContext
import junit.framework.TestCase.assertEquals
import org.junit.Test
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.data.model.Container
import rg.info.storagelocator.ui.components.searchContainers
import java.util.UUID

class SearchComponentTest {
    @Test
    fun searchComponent() {
        // val context
        val context = ApplicationProvider.getApplicationContext()


        val con = listOf(
            Container("A1", "First", "A1", UUID.randomUUID()),
            Container("A2", "Second", "A2", UUID.randomUUID()),
            Container("A3", "Third", "A3", UUID.randomUUID()),
        )

        Containers.deleteAllContainers()
        // when searching for a container

        // then we should get the container
        assertEquals(1, container.size)
        assertEquals("First", container[0].name)
    }
}