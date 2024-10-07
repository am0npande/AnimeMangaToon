package com.example.animemangatoon.screens.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animemangatoon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(modifier: Modifier = Modifier) {

    var search by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Search bar at the top
        SearchBar(
            modifier = Modifier.padding(horizontal = 5.dp),
            shape = SearchBarDefaults.fullScreenShape,
            query = search,
            onQueryChange = {search = it },
            onSearch = {},
            active = false,
            onActiveChange = {},
            trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") }
        ){}
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
        Column(modifier = Modifier.padding(16.dp)) {

            // Featured Section
            Text(
                "Featured",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // What to Watch Section
            Text(
                "What to Watch",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Latest News and Reviews
            Text(
                "Latest News and Reviews",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        HorizontalDivider()

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Contact Us",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Privacy Policy
            Text(
                "Privacy Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // About Us
            Text(
                "About Us",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Fact Checking Policy
            Text(
                "Fact Checking Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Corrections Policy
            Text(
                "Corrections Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Ethics Policy
            Text(
                "Ethics Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // DMCA Take Down Policy
            Text(
                "DMCA Take Down Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Editorial Policy
            Text(
                "Editorial Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Ownership Policy
            Text(
                "Ownership Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Terms of Use
            Text(
                "Terms of Use",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Footer()
    }
}


@Composable
fun Footer(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(1f)) // Push the footer to the bottom

        // Footer Section with Social Media Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {openLink(context, "https://www.facebook.com/profile.php?id=100070909602404")  }) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook Icon",
                    modifier = Modifier.size(50.dp) // You can adjust the size here
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {openLink(context, "https://x.com/pande_Amanifest") }) {
                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "Twitter Icon",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                openLink(context, "https://in.pinterest.com/workingweeks888/")
            }) {
                Image(
                    painter = painterResource(id = R.drawable.pinterest),
                    contentDescription = "Instagram Icon",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

private fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    context.startActivity(intent)
}

