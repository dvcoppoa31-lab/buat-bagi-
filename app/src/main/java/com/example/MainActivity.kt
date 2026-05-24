package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          LoginScreen(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  val purpleGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF1E1A33), Color(0xFF0F0B1E))
  )
  
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(purpleGradient),
    contentAlignment = Alignment.Center
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.9f)
        .padding(16.dp)
        .border(1.dp, Color(0xFF4A3485), RoundedCornerShape(24.dp)),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF151426))
    ) {
      Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Icon
        Box(
          modifier = Modifier
            .size(64.dp)
            .background(Color(0xFF2D2545), CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(Icons.Filled.AccountCircle, contentDescription = null, tint = Color(0xFF8B5CF6), modifier = Modifier.size(32.dp))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Title
        Text(
          text = "DRAG RACE",
          color = Color.White,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Fields
        LoginTextField(stringResource(id = R.string.username_hint), Icons.Filled.Person)
        Spacer(modifier = Modifier.height(16.dp))
        LoginTextField(stringResource(id = R.string.password_hint), Icons.Filled.Lock, isPassword = true)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Checkbox
        var checked by remember { mutableStateOf(true) }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(Alignment.Start)) {
          Checkbox(checked = checked, onCheckedChange = { checked = it }, colors = CheckboxDefaults.colors(checkedColor = Color(0xFF8B5CF6)), modifier = Modifier.testTag("remember_me_checkbox"))
          Text(text = stringResource(id = R.string.remember_me), color = Color.Gray)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Login Button
        Button(
          onClick = { /* TODO */ },
          modifier = Modifier.fillMaxWidth().height(50.dp).testTag("login_button"),
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(text = stringResource(id = R.string.login_action), fontWeight = FontWeight.Bold)
        }
        
        // Register Link
        TextButton(onClick = {
          val url = "https://wa.me/6281272791928?text=min%20mau%20beli%20no%20rek%20dong"
          val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
          context.startActivity(intent)
        }, modifier = Modifier.testTag("register_button")) {
          Text(text = stringResource(id = R.string.register_action), color = Color(0xFF8B5CF6))
        }
      }
    }
  }
}

@Composable
fun LoginTextField(hint: String, icon: androidx.compose.ui.graphics.vector.ImageVector, isPassword: Boolean = false) {
  var text by remember { mutableStateOf("") }
  OutlinedTextField(
    value = text,
    onValueChange = { text = it },
    placeholder = { Text(text = hint, color = Color.Gray, fontSize = 12.sp) },
    leadingIcon = { Icon(icon, contentDescription = null, tint = Color.Gray) },
    modifier = Modifier.fillMaxWidth().background(Color(0xFF1E1A33), RoundedCornerShape(12.dp)).testTag(hint.lowercase().replace(" ", "_")),
    shape = RoundedCornerShape(12.dp),
    visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
    colors = OutlinedTextFieldDefaults.colors(
      unfocusedContainerColor = Color(0xFF1E1A33),
      focusedContainerColor = Color(0xFF1E1A33),
      unfocusedBorderColor = Color.Transparent,
      focusedBorderColor = Color(0xFF8B5CF6)
    )
  )
}
