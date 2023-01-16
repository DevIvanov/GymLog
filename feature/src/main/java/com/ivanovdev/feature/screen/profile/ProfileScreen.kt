package com.ivanovdev.feature.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ivanovdev.feature.R
import com.ivanovdev.feature.ui.theme.*

@Composable
fun ProfileScreen() {
    val imageShape = RoundedCornerShape(L)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryDark)
            .padding(all = L)
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.profile_placeholder),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .aspectRatio(8f / 10f)
                    .clip(imageShape)
                    .background(color = Primary)
            )
            Column(modifier = Modifier.padding(start = XL)) {
                Text(
                    modifier = Modifier.padding(bottom = S),
                    text = "First Name",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = TextXL
                )
                Text(
                    modifier = Modifier.padding(bottom = L),
                    text = "Last Name",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = TextXL
                )
                Text(
                    text = "Weight: 83kg",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = TextXL
                )
            }
        }
        Row(Modifier.padding(top = XL),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dark theme",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}