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

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.example.dashboard_ewaste_android.data.model.Dropbox
import com.example.dashboard_ewaste_android.data.model.Poin
import com.example.dashboard_ewaste_android.data.model.WasteItem
import com.example.dashboard_ewaste_android.data.model.Approval
import com.example.dashboard_ewaste_android.ui.screens.approval.ApprovalViewModel
import androidx.compose.ui.text.style.TextAlign
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.LaunchedEffect


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    approvalViewModel: ApprovalViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadAllData()
    }

    val allDropboxes by viewModel.allDropboxes.collectAsStateWithLifecycle()
    val allPoin by viewModel.allPoin.collectAsStateWithLifecycle()
    val allWasteItems by viewModel.allWasteItems.collectAsStateWithLifecycle()

    val allApprovals by approvalViewModel.approvals.collectAsStateWithLifecycle()
    val pendingApprovals = remember(allApprovals) {
        allApprovals.filter { it.status == "Pending" }
    }

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
            StatsCard(
                dropboxCount = allDropboxes.size,
                totalPoin = allPoin.sumOf { it.jumlahPoin },
                wasteItemCount = allWasteItems.size
            )
        }
        item {
            ApprovalSection(
                pendingApprovals = pendingApprovals,
                onApprove = { approvalViewModel.approveApproval(it) },
                onReject = { approvalViewModel.rejectApproval(it) },
                onViewDetail = { /* TODO: Implement view detail */ }
            )
        }
    }
}

@Composable
fun StatsCard(dropboxCount: Int, totalPoin: Int, wasteItemCount: Int) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Laporan Pengelolaan Sampah", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Total Dropboxes: $dropboxCount")
                    Text("Total Poin Terkumpul: $totalPoin")
                    Text("Jumlah Sampah Terdaftar: $wasteItemCount")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Grafik Laporan di sini")
                }
            }
        }
    }
}

@Composable
fun ApprovalSection(
    pendingApprovals: List<Approval>,
    onApprove: (Approval) -> Unit,
    onReject: (Approval) -> Unit,
    onViewDetail: (Approval) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Request Approval", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        if (pendingApprovals.isEmpty()) {
            Text(
                "Tidak ada permintaan approval baru.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        } else {
            pendingApprovals.forEach { approval ->
                ApprovalItemCard(
                    approval = approval,
                    onApprove = onApprove,
                    onReject = onReject,
                    onViewDetail = onViewDetail
                )
            }
        }
    }
}

@Composable
fun ApprovalItemCard(
    approval: Approval,
    onApprove: (Approval) -> Unit,
    onReject: (Approval) -> Unit,
    onViewDetail: (Approval) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${approval.namaPengaju} (${approval.tipeApproval})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Status: ${approval.status}",
                style = MaterialTheme.typography.bodyMedium,
                color = when (approval.status) {
                    "Approved" -> MaterialTheme.colorScheme.primary
                    "Rejected" -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Diajukan pada: ${
                        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                            .format(Date(approval.tanggalPengajuan))
                    }",
                    style = MaterialTheme.typography.bodySmall
                )
                Row {
                    IconButton(onClick = { onViewDetail(approval) }) {
                        Icon(Icons.Default.Visibility, contentDescription = "Lihat Detail")
                    }
                    if (approval.status == "Pending") {
                        IconButton(onClick = { onApprove(approval) }) {
                            Icon(Icons.Default.CheckCircle, contentDescription = "Setujui", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { onReject(approval) }) {
                            Icon(Icons.Default.Close, contentDescription = "Tolak", tint = MaterialTheme.colorScheme.error)
                        }
                    }
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