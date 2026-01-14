package dev.tomislavmiksik.phoenix.ui.main.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.tomislavmiksik.phoenix.ui.main.DashboardRoute
import dev.tomislavmiksik.phoenix.ui.main.ProfileRoute

enum class BottomNavDestination(
    val route: Any,
    val icon: ImageVector,
    val label: String
) {
    Dashboard(
        route = DashboardRoute,
        icon = Icons.Default.Home,
        label = "Dashboard"
    ),
    Profile(
        route = ProfileRoute,
        icon = Icons.Default.Person,
        label = "Profile"
    )
}

@Composable
fun BottomNavBar(
    currentDestination: BottomNavDestination,
    onDestinationSelected: (BottomNavDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.shadow(elevation = 8.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        windowInsets = WindowInsets(0.dp)
    ) {
        BottomNavDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == destination,
                alwaysShowLabel = false,
                onClick = { onDestinationSelected(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },
                label = { Text(destination.label) }
            )
        }
    }
}
