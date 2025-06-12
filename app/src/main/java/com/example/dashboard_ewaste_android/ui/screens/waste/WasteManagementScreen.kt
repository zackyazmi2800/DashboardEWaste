package com.example.dashboard_ewaste_android.ui.screens.waste

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dashboard_ewaste_android.data.model.WasteItem

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WasteManagementScreen(
    viewModel: WasteViewModel = hiltViewModel()
) {
    val wasteItems by viewModel.wasteItems.collectAsStateWithLifecycle()

    var showAddWasteDialog by remember { mutableStateOf(false) }
    var wasteNameInput by remember { mutableStateOf("") }
    var wasteWeightInput by remember { mutableStateOf("") }
    var wasteQtyInput by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Manajemen Sampah") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddWasteDialog = true }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah Sampah")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Tambah")
                }
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    WasteCategory(
                        categoryName = "Semua Sampah Terdaftar",
                        items = wasteItems.map { "${it.nama} (${it.berat}kg, ${it.qty}pcs)" }
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

        // Panggil dialog jika showAddWasteDialog true
        if (showAddWasteDialog) {
            AddWasteDialog(
                wasteName = wasteNameInput,
                onWasteNameChange = { wasteNameInput = it },
                wasteWeight = wasteWeightInput,
                onWasteWeightChange = { wasteWeightInput = it },
                wasteQty = wasteQtyInput,
                onWasteQtyChange = { wasteQtyInput = it },
                onAddWaste = {
                    val name = wasteNameInput.trim()
                    val weight = wasteWeightInput.toIntOrNull()
                    val qty = wasteQtyInput.toIntOrNull()

                    if (name.isNotBlank() && weight != null && qty != null) {
                        viewModel.addWasteItem(WasteItem(nama = name, berat = weight, qty = qty))
                        wasteNameInput = ""
                        wasteWeightInput = ""
                        wasteQtyInput = ""
                        showAddWasteDialog = false
                    } else {
                        // Anda bisa menambahkan Toast atau Snackbar di sini untuk pesan error
                    }
                },
                onDismiss = {
                    showAddWasteDialog = false
                    wasteNameInput = ""
                    wasteWeightInput = ""
                    wasteQtyInput = ""
                }
            )
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

@Composable
fun AddWasteDialog(
    wasteName: String,
    onWasteNameChange: (String) -> Unit,
    wasteWeight: String,
    onWasteWeightChange: (String) -> Unit,
    wasteQty: String,
    onWasteQtyChange: (String) -> Unit,
    onAddWaste: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tambah Sampah Baru") },
        text = {
            Column {
                OutlinedTextField(
                    value = wasteName,
                    onValueChange = onWasteNameChange,
                    label = { Text("Nama Sampah") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = wasteWeight,
                    onValueChange = onWasteWeightChange,
                    label = { Text("Berat (KG)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = wasteQty,
                    onValueChange = onWasteQtyChange,
                    label = { Text("Jumlah (Pcs)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onAddWaste) {
                Text("Tambah")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun WasteManagementScreenPreview() {
    DashboardEwasteAndroidTheme {
        WasteManagementScreen()
    }
}