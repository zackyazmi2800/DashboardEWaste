package com.example.dashboard_ewaste_android.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

@Composable
fun DashboardScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Dashboard", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
        item {
            StatsCard() // Placeholder for chart and progress
        }
        item {
            ApprovalSection() // Combined section for approvals
        }
    }
}

@Composable
fun StatsCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Laporan Pengelolaan Sampah", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            // Placeholder for the graph
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Grafik Laporan di sini")
            }
        }
    }
}

@Composable
fun ApprovalSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Request Approval", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        // Based on "Gambar 31 Prototipe Halaman Approval Masyarakat"
        ApprovalItemCard("Zacky Azmi")
        ApprovalItemCard("Aldi Maulana Fadilah")
        ApprovalItemCard("Umar")
    }
}

@Composable
fun ApprovalItemCard(name: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
            Row {
                IconButton(onClick = { /* TODO: View action */ }) {
                    Icon(Icons.Default.Visibility, contentDescription = "Lihat Detail")
                }
                IconButton(onClick = { /* TODO: Approve action */ }) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Setujui", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = { /* TODO: Reject action */ }) {
                    Icon(Icons.Default.Close, contentDescription = "Tolak", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardEwasteAndroidTheme {
        DashboardScreen()
    }
}