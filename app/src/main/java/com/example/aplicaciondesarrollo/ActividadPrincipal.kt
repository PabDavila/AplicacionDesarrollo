package com.example.aplicaciondesarrollo

class MainActivity : ComponentActivity() {
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aplicacion()
        }
    }
}
