package com.example.composepratice

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepratice.ui.theme.ComposePraticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePraticeTheme {
                var count = remember {
                    mutableIntStateOf(0)
                }
                var name = remember {
                    mutableStateOf("")
                }
                var namesList = remember {
                    mutableStateOf(listOf<String>())
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopHeading()
//                Scaffold()
//                Counter(count)
                    DynamicListPractice(name, namesList)
                }
            }
        }
    }

    @Composable
    private fun TopHeading() {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(20.dp),
            text = stringResource(R.string.this_is_practice_app_for_jetpack_compose_learning),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }

    @Composable
    private fun Counter(count: MutableIntState) {
        Text(
            text = count.intValue.toString(), fontSize = 30.sp
        )
        Button(modifier = Modifier.padding(20.dp), onClick = {
            count.intValue++
        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.icon_for_addition_button)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier.padding(20.dp), onClick = {
            count.intValue = 0
        }) {
            Text(text = stringResource(R.string.reset))
        }
    }

    @Composable
    private fun DynamicListPractice(
        name: MutableState<String>, namesList: MutableState<List<String>>
    ) {
        OutlinedTextField(label = {
            Text("Enter your name", color = Color.Gray)
        }, value = name.value, onValueChange = { value ->
            name.value = value
        })
        Button(modifier = Modifier.padding(20.dp), onClick = {
            if (name.value.isNotBlank()) {
                namesList.value += name.value
                name.value = ""
            }
        }) {
            Text(
                text = stringResource(R.string.add_name_to_list),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
        Button(onClick = {
            if (namesList.value.isNotEmpty()) {
                namesList.value = namesList.value.dropLast(1)
            }
        }) {
            Text(
                text = stringResource(R.string.remove_name_from_list),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.the_names_list_will_show_here),
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(namesList.value) { individualName ->
                Text(
                    text = individualName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
                Divider()
            }
        }
    }

    @Composable
    private fun Scaffold() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = stringResource(R.string.world), modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                color = Color.White,
                text = "Hello $name!",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(15.dp)
            )
            Text(text = "Hello $name!",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clickable { onTextClicked() }
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .padding(15.dp))
        }
        Text(
            color = Color.White,
            text = "Hello $name!",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(15.dp)
        )
        Text(text = "Hello $name!",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable { onTextClicked() }
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(15.dp))
    }
}

fun onTextClicked() {
    println("onTextClicked")
}

@Composable
fun MatchParentSizeComposable() {
    Box {
        Spacer(
            Modifier
                .matchParentSize()
                .background(Color.Gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnsPractice() {
    Row {
        Column {
//        for (i in 1..2)
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
        Column {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
        Column {
//        for (i in 1..2)
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImagesPractice() {
    Image(
        painter = painterResource(id = R.drawable.icon_success),
        contentDescription = stringResource(R.string.a_practice_image),
        modifier = Modifier.background(Color.Gray)
    )
}

@Preview(showBackground = true)
@Composable
fun BoxPractice() {
    Image(
        painter = painterResource(id = R.drawable.icon_success),
        contentDescription = stringResource(R.string.a_practice_image),
        modifier = Modifier.background(Color.Gray)
    )
}

@Preview(showBackground = true)
@Composable
fun LazyColumnPractice() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.light_purple))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.light_blue))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.dark_blue))
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.dark_blue))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.light_purple))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.light_blue))
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.light_blue))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.dark_blue))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(colorResource(R.color.light_purple))
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BoxGrid() {
    val colors = listOf(
        colorResource(id = R.color.light_purple),
        colorResource(id = R.color.light_blue),
        colorResource(id = R.color.dark_blue),
        colorResource(id = R.color.dark_blue),
        colorResource(id = R.color.light_purple),
        colorResource(id = R.color.light_blue),
        colorResource(id = R.color.light_blue),
        colorResource(id = R.color.dark_blue),
        colorResource(id = R.color.light_purple)
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        for (i in 0 until 3) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                for (j in 0 until 3) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .background(colors[i * 3 + j])
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyRowPractice() {
    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(10) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePraticeTheme {
        Greeting(stringResource(R.string.world))
    }
}