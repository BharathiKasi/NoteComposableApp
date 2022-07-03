package com.example.notecomposeapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notecomposeapp.feature_note.presentation.add_edit_note_screen.components.AddEditNoteScreen
import com.example.notecomposeapp.feature_note.presentation.notes_screen.components.NoteScree
import com.example.notecomposeapp.feature_note.presentation.ui.theme.NoteComposeAppTheme
import com.example.notecomposeapp.feature_note.presentation.utill.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val lNavController = rememberNavController()
                    NavHost(navController = lNavController, startDestination = Screen.NoteScreen.route){
                        composable(route = Screen.NoteScreen.route){
                            NoteScree(navController = lNavController)
                        }
                        composable(route = Screen.AddEditNoteScreen.route+"?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(navArgument(
                            name = "noteid"){
                            type = NavType.IntType
                            defaultValue = -1
                        }, navArgument(
                                name = "noteColor"){
                                type = NavType.IntType
                                defaultValue = -1
                        })){
                            val lColor = it.arguments?.getInt("noteColor")?:-1
                            AddEditNoteScreen(pNavController = lNavController, pNoteColor =lColor )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteComposeAppTheme {
        Greeting("Android")
    }
}