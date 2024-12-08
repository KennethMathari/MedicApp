package com.mobile.medicapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.medicapp.ui.model.MedicinePresentation

@Composable
fun MedicineDetail(
    medicinePresentation: MedicinePresentation,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                InfoRow(label = "Name:", value = medicinePresentation.name)
                InfoRow(label = "Dose:", value = medicinePresentation.dose)
                InfoRow(label = "Strength:", value = medicinePresentation.strength)
                InfoRow(label = "Fequency:", value = medicinePresentation.frequency)
                InfoRow(label = "Use:", value = medicinePresentation.use)
                InfoRow(label = "Route:", value = medicinePresentation.route)
            }
        }
    }
}