package com.francis.drivedeal.ui.screens.auth


import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.francis.drivedeal.R
import com.francis.drivedeal.model.User
import com.francis.drivedeal.navigation.ROUT_LOGIN
import com.francis.drivedeal.ui.theme.newblue
import com.francis.drivedeal.ui.theme.newwhite
import com.francis.drivedeal.viewmodel.AuthViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {


  Column(modifier = Modifier.background(newwhite)
      .fillMaxSize()){
      //CARD
      Card(
          modifier = Modifier
              .fillMaxWidth()
              .height(300.dp),
          shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF0D47A1)) // Matching deep blue
      ) {
          Box(
              modifier = Modifier
                  .fillMaxSize()
          ) {
              // Semi-transparent background overlay image inside the card


              Column(
                  modifier = Modifier
                      .fillMaxSize()
                      .padding(top = 30.dp),
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Top
              ) {
                  // Elevated centered logo
                  Image(
                      painter = painterResource(id = R.drawable.login),
                      contentDescription = "Login Icon",
                      contentScale = ContentScale.Fit,
                      modifier = Modifier
                          .size(180.dp)
                          .shadow(10.dp, CircleShape)
                          .background(Color.White, CircleShape)
                          .padding(20.dp)
                  )

                  Spacer(modifier = Modifier.height(12.dp))

                  Text(
                      text = "Welcome to DriveDeal",
                      color = Color.White,
                      fontSize = 22.sp,
                      fontWeight = FontWeight.Bold,
                      fontFamily = FontFamily.Serif
                  )
              }
          }
      }



      var username by remember { mutableStateOf("") }
      var email by remember { mutableStateOf("") }
      var password by remember { mutableStateOf("") }
      var confirmPassword by remember { mutableStateOf("") }
      var passwordVisible by remember { mutableStateOf(false) }
      var confirmPasswordVisible by remember { mutableStateOf(false) }

      val roleOptions = listOf("buyer", "seller")
      var expanded by remember { mutableStateOf(false) }

      val context = LocalContext.current
      val animatedAlpha by animateFloatAsState(
          targetValue = 1f,
          animationSpec = tween(durationMillis = 1500, easing = LinearEasing),
          label = "Animated Alpha"
      )


      // 🔵 Foreground Content
      Box(
          modifier = Modifier
              .fillMaxSize()
              .background(newwhite) // Light

      ) {
          Column(
          modifier = Modifier
              .padding(16.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,

          ) {

          AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
              Text(
                  "Create Your Account",
                  fontSize = 40.sp,
                  fontFamily = FontFamily.Cursive,
                  color = Color.Black // 🖤 Text in black
              )
          }
          Spacer(modifier = Modifier.height(10.dp))

          // Username
          OutlinedTextField(
              value = username,
              onValueChange = { username = it },
              label = { Text("Username", color = Color.Black) },
              leadingIcon = {
                  Icon(Icons.Filled.Person, contentDescription = "Username Icon", tint = Color.Blue)
              },

              modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(8.dp))

          // Email
          OutlinedTextField(
              value = email,
              onValueChange = { email = it },
              label = { Text("Email", color = Color.Black) },
              leadingIcon = {
                  Icon(Icons.Filled.Email, contentDescription = "Email Icon", tint = Color.Blue)
              },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
              modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(8.dp))

          // Role Dropdown
          ExposedDropdownMenuBox(
              expanded = expanded,
              onExpandedChange = { expanded = !expanded }
          ) {


          }

          Spacer(modifier = Modifier.height(8.dp))

          // Password
          OutlinedTextField(
              value = password,
              onValueChange = { password = it },
              label = { Text("Password", color = Color.Black) },
              visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
              leadingIcon = {
                  Icon(Icons.Filled.Lock, contentDescription = "Password Icon", tint = Color.Blue)
              },
              trailingIcon = {
                  val image = if (passwordVisible) painterResource(R.drawable.visibility) else painterResource(R.drawable.visibilityoff)
                  IconButton(onClick = { passwordVisible = !passwordVisible }) {
                      Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password", tint = Color.Blue)
                  }
              },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

              modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(8.dp))

          // Confirm Password
          OutlinedTextField(
              value = confirmPassword,
              onValueChange = { confirmPassword = it },
              label = { Text("Confirm Password", color = Color.Black) },
              visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
              leadingIcon = {
                  Icon(Icons.Filled.Lock, contentDescription = "Confirm Password Icon", tint = Color.Blue)
              },
              trailingIcon = {
                  val image = if (confirmPasswordVisible) painterResource(R.drawable.visibility) else painterResource(R.drawable.visibilityoff)
                  IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                      Icon(image, contentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password", tint = Color.Blue)
                  }
              },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

              modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Register Button
          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(50.dp)
                  .background(
                      brush = Brush.horizontalGradient(
                          colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
                      ),
                      shape = MaterialTheme.shapes.medium
                  ),
              contentAlignment = Alignment.Center
          ) {
              Button(
                  onClick = {
                      if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                          Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                      } else if (password != confirmPassword) {
                          Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                      } else {
                          authViewModel.registerUser(
                              User(username = username, email = email,  password = password)
                          )
                          onRegisterSuccess()
                      }
                  },
                  modifier = Modifier.fillMaxSize(),
                  colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
              ) {
                  Text("Register", color = Color.Black) // 🖤 Text is black
              }
          }

          Spacer(modifier = Modifier.height(10.dp))

          TextButton(onClick = { navController.navigate(ROUT_LOGIN) }) {
              Text("Already have an account? Login", color = Color.Black) // 🖤 Text
          }
      }


      }

  }
}
