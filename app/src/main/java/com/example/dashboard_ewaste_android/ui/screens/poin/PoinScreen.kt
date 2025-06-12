package com.example.dashboard_ewaste_android.ui.screens.poin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

import androidx.hilt.navigation.compose.hiltViewModel // Import HiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle // Import for observing Flow
import com.example.dashboard_ewaste_android.data.model.Poin // Import Poin model
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoinScreen(
    viewModel: PoinViewModel = hiltViewModel() // Inject PoinViewModel
) {
    val poinData by viewModel.poinData.collectAsStateWithLifecycle() // Observe poin data

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Manajemen Poin") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Total Poin Anda:", style = MaterialTheme.typography.headlineSmall)
            // Menampilkan total poin
            val totalPoin = poinData.sumOf { it.jumlahPoin }
            Text("$totalPoin Poin", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Riwayat Poin:", style = MaterialTheme.typography.titleMedium)
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (poinData.isEmpty()) {
                    item {
                        Text("Belum ada riwayat poin.", modifier = Modifier.fillMaxWidth(), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    }
                } else {
                    items(poinData) { poin ->
                        PoinItemCard(poin = poin)
                    }
                }
            }

            // Tombol untuk menambahkan poin dummy (untuk pengujian)
            Button(onClick = {
                viewModel.addPoin(Poin(jumlahPoin = (10..100).random())) // Tambah poin acak
            }) {
                Text("Tambah Poin")
            }
        }
    }
}

@Composable
fun PoinItemCard(poin: Poin) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Poin ID: ${poin.idPoin}", style = MaterialTheme.typography.bodyMedium)
            Text("${poin.jumlahPoin} Poin", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PoinScreenPreview() {
    DashboardEwasteAndroidTheme {
        // Untuk Preview, Anda bisa menyediakan ViewModel dummy atau data dummy
        PoinScreen()
    }
}