package com.mobile.medicapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InfoRow(
    label: String, value: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(vertical = 2.dp)) {
        Text(text = label)
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}