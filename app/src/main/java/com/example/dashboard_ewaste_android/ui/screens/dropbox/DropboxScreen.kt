package com.example.dashboard_ewaste_android.ui.screens.dropbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Import for destructuring assignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

import androidx.hilt.navigation.compose.hiltViewModel // Import HiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle // Import for observing Flow
import com.example.dashboard_ewaste_android.data.model.Dropbox // Import Dropbox model

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropboxScreen(
    viewModel: DropboxViewModel = hiltViewModel() // Inject DropboxViewModel
) {
    // Collect data from ViewModel
    val dropboxItems by viewModel.dropboxItems.collectAsStateWithLifecycle() //

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

            if (dropboxItems.isEmpty()) {
                item {
                    Text("Tidak ada Drop Box tersedia.", modifier = Modifier.padding(16.dp))
                }
            }
            // Tombol untuk menambahkan dropbox dummy (untuk pengujian)
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    viewModel.addDropbox(
                        Dropbox(
                            nama = "DROP-BOX BARU",
                            lokasi = "Lokasi Random ${System.currentTimeMillis() % 100}",
                            kapasitas = 1000 // Kapasitas default
                        )
                    )
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Tambah Drop Box Dummy")
                }
            }
        }
    }
}


@Composable
fun DropboxItemCard(item: Dropbox) { //
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(item.nama, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Lokasi: ${item.lokasi}", style = MaterialTheme.typography.bodyMedium)
                Text("Jumlah Kurir: ???", style = MaterialTheme.typography.bodyMedium)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Live E-SAMPAH: ${item.kapasitas} KG", style = MaterialTheme.typography.bodyMedium) // Menggunakan kapasitas sebagai contoh
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
        // Untuk Preview, Anda bisa menyediakan ViewModel dummy atau data dummy
        DropboxScreen()
    }
}