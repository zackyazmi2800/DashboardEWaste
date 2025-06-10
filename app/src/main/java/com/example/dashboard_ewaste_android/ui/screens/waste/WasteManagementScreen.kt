package com.example.dashboard_ewaste_android.ui.screens.waste

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

import androidx.hilt.navigation.compose.hiltViewModel // Import HiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle // Import for observing Flow
import com.example.dashboard_ewaste_android.data.model.WasteItem // Import WasteItem model

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WasteManagementScreen(
    viewModel: WasteViewModel = hiltViewModel() // Inject WasteViewModel
) {
    val wasteItems by viewModel.wasteItems.collectAsStateWithLifecycle() // Observe waste items

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Manajemen Sampah") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                WasteCategory(
                    categoryName = "Semua Sampah Terdaftar",
                    items = wasteItems.map { "${it.nama} (${it.berat}kg, ${it.qty}pcs)" } // Mengubah WasteItem menjadi String
                )
            }
         
            item {
                WasteCategory(
                    categoryName = "Sampah Ponsel",
                    items = wasteItems.filter { it.nama.contains("Ponsel", ignoreCase = true) }.map { "${it.nama} (${it.berat}kg, ${it.qty}pcs)" }
                )
            }
            item {
                WasteCategory(
                    categoryName = "Sampah TV/Monitor",
                    items = wasteItems.filter { it.nama.contains("TV", ignoreCase = true) || it.nama.contains("Monitor", ignoreCase = true) }.map { "${it.nama} (${it.berat}kg, ${it.qty}pcs)" }
                )
            }
            item {
                WasteCategory(
                    categoryName = "Sampah Komputer",
                    items = wasteItems.filter { it.nama.contains("Komputer", ignoreCase = true) }.map { "${it.nama} (${it.berat}kg, ${it.qty}pcs)" }
                )
            }

        }
    }
}


@Composable
fun WasteCategory(categoryName: String, items: List<String>) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(categoryName, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Tutup" else "Buka"
                )
            }
            if (expanded) {
                Divider()
                if(items.isEmpty()) {
                    Text("Tidak ada sampah", modifier = Modifier.padding(16.dp))
                } else {
                    items.forEach { item ->
                        WasteItemRow(itemName = item)
                    }
                }
            }
        }
    }
}

@Composable
fun WasteItemRow(itemName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(itemName, style = MaterialTheme.typography.bodyMedium)
        Button(onClick = { /* TODO: Jemput action */ }) {
            Text("Jemput")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WasteManagementScreenPreview() {
    DashboardEwasteAndroidTheme {
        // Untuk Preview, Anda bisa menyediakan ViewModel dummy atau data dummy
        WasteManagementScreen()
    }
}