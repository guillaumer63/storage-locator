package rg.info.storagelocator.ui.components.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.data.model.Container

@Composable
fun SearchComponent(search: String, navController: NavController) {
    val containersToShow = searchContainers(search)
    ListComponent(containersToShow, navController)
}

fun searchContainers(search: String): List<Container> {
    var containersToShow = emptyList<Container>()
    // create a empty list of containers

    // The list of containers is filtered based on the search query.
    // The search query is compared with :
    // 1. The name of the container
    // 2. The name of the items in the container
    // 3. The name of the container using the levenshtein distance algorithm
    // 4. The name of the items in the container using the levenshtein distance algorithm
    containersToShow = containersToShow.plus(Containers.getContainers().filter {
        it.name.contains(search, true)
                || it.getItems().any { item -> item.contains(search, true) }
                || levenshteinDistanceSimilar(search, it.name)
                || it.getItems().any { item -> levenshteinDistanceSimilar(search, item) }
    })

    return containersToShow
}

// Levenshtein distance algorithm
fun levenshteinDistanceSimilar(s: String, t: String): Boolean {
    if (s == t) return true
    val d = Array(s.length + 1) { IntArray(t.length + 1) }
    for (i in 0..s.length) d[i][0] = i
    for (j in 0..t.length) d[0][j] = j
    for (i in 1..s.length) {
        for (j in 1..t.length) {
            d[i][j] = minOf(
                d[i - 1][j] + 1,
                d[i][j - 1] + 1,
                d[i - 1][j - 1] + if (s[i - 1] == t[j - 1]) 0 else 1
            )
        }
    }
    return d[s.length][t.length] <= s.length * 0.9
}