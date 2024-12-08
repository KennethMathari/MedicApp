package com.mobile.medicapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobile.medicapp.data.network.model.Medicine
import com.mobile.medicapp.ui.state.DashboardState
import com.mobile.medicapp.ui.viewmodel.DashboardViewModel

@Composable
fun MedicineList(
    modifier: Modifier = Modifier,
    dashboardState: DashboardState,
    dashboardViewModel: DashboardViewModel,
    onMedicineClicked: (Medicine) -> Unit
) {
    val user = Firebase.auth.currentUser?.email
    val greeting = dashboardViewModel.getGreeting()

    var searchMedicine by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Greeting
        Text(
            text = "$greeting, $user!",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp)
        )

        // Search Bar
        OutlinedTextField(
            value = searchMedicine,
            onValueChange = {
                searchMedicine = it
                dashboardViewModel.onSearchMedicine(it)
            },
            label = { Text(text = "Search") },
            placeholder = { Text(text = "Search for medicine...") },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Medicine List
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp), modifier = Modifier.fillMaxSize()
        ) {
            items(dashboardState.medicineList.orEmpty()) { medicine ->
                MedicineCard(
                    medicine = medicine, onMedicineClicked = onMedicineClicked
                )
            }
        }
    }
}

@Composable
fun MedicineCard(
    medicine: Medicine,
    onMedicineClicked: (Medicine) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onMedicineClicked(medicine) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            InfoRow(label = "Name:", value = medicine.name)
            InfoRow(label = "Dose:", value = medicine.dose)
            InfoRow(label = "Strength:", value = medicine.strength)
        }
    }
}
