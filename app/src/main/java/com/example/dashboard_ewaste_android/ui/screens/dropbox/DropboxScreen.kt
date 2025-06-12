package com.example.dashboard_ewaste_android.ui.screens.dropbox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dashboard_ewaste_android.data.model.Dropbox
import kotlinx.coroutines.launch
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropboxScreen(
    viewModel: DropboxViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadDropboxes()
    }

    val dropboxItems by viewModel.dropboxItems.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Monitoring Drop Box") })
        },
        // FLOATING ACTION BUTTON DIHAPUS DARI SINI
        // floatingActionButton = { ... }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (dropboxItems.isEmpty()) {
                item {
                    Text("Tidak ada Drop Box tersedia.", modifier = Modifier.fillMaxWidth().padding(16.dp), textAlign = TextAlign.Center)
                }
            } else {
                items(dropboxItems) { item ->
                    DropboxItemCard(
                        item = item,
                        onDelete = { viewModel.deleteDropbox(item) }
                    )
                }
            }

            // <--- TAMBAHKAN BUTTON BIASA DI SINI SEBAGAI ITEM TERAKHIR DARI LAZYCOLUMN
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Spasi dari item terakhir
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.addDropbox(
                                Dropbox(
                                    nama = "DROP-BOX BARU ${System.currentTimeMillis() % 1000}",
                                    lokasi = "Lokasi Random",
                                    kapasitas = 1000
                                )
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp) // Sesuaikan tinggi button
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah Drop Box Dummy")
                    Spacer(Modifier.width(8.dp))
                    Text("Tambah Drop Box Dummy")
                }
                Spacer(modifier = Modifier.height(16.dp)) // Spasi di bagian bawah
            }
            // --- AKHIR PENAMBAHAN BUTTON ---
        }
    }
}

@Composable
fun DropboxItemCard(item: Dropbox, onDelete: (Dropbox) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(item.nama, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Lokasi: ${item.lokasi}", style = MaterialTheme.typography.bodyMedium)
                Text("Jumlah Kurir: ???", style = MaterialTheme.typography.bodyMedium)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Live E-SAMPAH: ${item.kapasitas} KG", style = MaterialTheme.typography.bodyMedium)
                Row {
                    Button(onClick = { /* TODO: Kirim action */ }) {
                        Text("Kirim")
                    }
                    Spacer(Modifier.width(8.dp))
                    IconButton(onClick = { onDelete(item) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Hapus", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropboxScreenPreview() {
    DashboardEwasteAndroidTheme {
        Text("Preview DropboxScreen (dengan button)")
    }
}