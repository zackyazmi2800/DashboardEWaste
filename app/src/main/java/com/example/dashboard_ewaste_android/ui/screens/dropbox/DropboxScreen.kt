package com.example.dashboard_ewaste_android.ui.screens.dropbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

data class DropboxItem(val name: String, val location: String, val courierCount: Int, val liveLoad: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropboxScreen() {
    val dropboxItems = listOf(
        DropboxItem("DROP-BOX SUKASARI", "SUKASARI", 15, "1 TON"),
        DropboxItem("DROP-BOX SUKAJADI", "SUKAJADI", 12, "1 TON"),
        DropboxItem("DROP-BOX ANDIR", "ANDIR", 16, "1 TON")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Monitoring Drop Box") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dropboxItems) { item ->
                DropboxItemCard(item = item)
            }
        }
    }
}

@Composable
fun DropboxItemCard(item: DropboxItem) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(item.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Lokasi: ${item.location}", style = MaterialTheme.typography.bodyMedium)
                Text("Jumlah Kurir: ${item.courierCount}", style = MaterialTheme.typography.bodyMedium)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Live E-SAMPAH: ${item.liveLoad}", style = MaterialTheme.typography.bodyMedium)
                Button(onClick = { /* TODO: Kirim action */ }) {
                    Text("Kirim")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropboxScreenPreview() {
    DashboardEwasteAndroidTheme {
        DropboxScreen()
    }
}