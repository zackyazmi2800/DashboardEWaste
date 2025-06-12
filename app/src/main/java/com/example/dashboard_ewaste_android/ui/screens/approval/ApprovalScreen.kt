package com.example.dashboard_ewaste_android.ui.screens.approval

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Add // <--- TAMBAHKAN BARIS INI
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dashboard_ewaste_android.data.model.Approval // Impor model Approval

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApprovalScreen(
    viewModel: ApprovalViewModel = hiltViewModel() // Inject ApprovalViewModel
) {
    val approvals by viewModel.approvals.collectAsStateWithLifecycle() // Amati daftar approval

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Request Approval") })
        },
        floatingActionButton = { // Tombol untuk menambahkan approval dummy (untuk pengujian)
            FloatingActionButton(onClick = {
                viewModel.addApproval(
                    Approval(
                        namaPengaju = "User Baru ${System.currentTimeMillis() % 1000}",
                        tipeApproval = if (System.currentTimeMillis() % 2 == 0L) "Masyarakat" else "Kurir",
                        status = "Pending"
                    )
                )
            }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Approval Dummy")
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (approvals.isEmpty()) {
                item {
                    Text(
                        "Tidak ada permintaan approval.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            } else {
                items(approvals) { approval ->
                    ApprovalItemCard(
                        approval = approval,
                        onApprove = { viewModel.approveApproval(approval) },
                        onReject = { viewModel.rejectApproval(approval) },
                        onViewDetail = { /* TODO: Implement view detail */ }
                    )
                }
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
                        java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
                            .format(java.util.Date(approval.tanggalPengajuan))
                    }",
                    style = MaterialTheme.typography.bodySmall
                )
                Row {
                    IconButton(onClick = { onViewDetail(approval) }) {
                        Icon(Icons.Default.Visibility, contentDescription = "Lihat Detail")
                    }
                    // Tombol aksi hanya aktif jika status masih Pending
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
fun ApprovalScreenPreview() {
    DashboardEwasteAndroidTheme {
        ApprovalScreen()
    }
}