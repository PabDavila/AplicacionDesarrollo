package com.example.aplicaciondesarrollo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aplicacion()
        }
    }
}
