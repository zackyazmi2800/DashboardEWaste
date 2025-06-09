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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WasteManagementScreen() {
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
                    categoryName = "Sampah Ponsel",
                    items = listOf("Ponsel Poco X3 Pro Mati")
                )
            }
            item {
                WasteCategory(categoryName = "Sampah TV/Monitor", items = emptyList())
            }
            item {
                WasteCategory(categoryName = "Sampah Komputer", items = emptyList())
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
                items.forEach { item ->
                    WasteItemRow(itemName = item)
                }
                if(items.isEmpty()) {
                    Text("Tidak ada sampah", modifier = Modifier.padding(16.dp))
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
        WasteManagementScreen()
    }
}