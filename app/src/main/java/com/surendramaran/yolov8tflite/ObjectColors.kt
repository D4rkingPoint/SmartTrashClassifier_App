package com.surendramaran.yolov8tflite

import android.graphics.Color

object ObjectColors {
    // Definimos los colores en formato ARGB (Alpha, Red, Green, Blue)
    // También almacenamos el nombre del color del basurero para referencia
    val paper: Pair<Int, String> = Pair(Color.BLUE, "Azul") // Azul (para papel)
    val cardboard: Pair<Int, String> = Pair(Color.BLUE, "Azul") // Azul (para cartón)
    val plastic: Pair<Int, String> = Pair(Color.YELLOW, "Amarillo") // Amarillo (para plástico)
    val metal: Pair<Int, String> = Pair(Color.GRAY, "Gris") // Gris (para metal)
    val glass: Pair<Int, String> = Pair(Color.GREEN, "Verde") // Verde (para vidrio)

    // 'trash' no estaba en tu lista inicial de labels.txt, pero sí en tu lista de colores.
    // La he incluido aquí por si la necesitas.
    val trash: Pair<Int, String> = Pair(Color.BLACK, "Negro") // Negro (para basura general)

    // Para 'compostable' (Café), usaremos un color RGB personalizado.
    // Color.rgb(165, 42, 42) es un marrón rojizo.
    // Si necesitas un café más oscuro o diferente, puedes ajustar estos valores.
    // El orden es (Red, Green, Blue). Tu valor original era (42, 42, 165) que es un azul oscuro.
    // Asumo que querías un tono café, así que he ajustado los valores RGB.
    val compostable: Pair<Int, String> = Pair(Color.rgb(165, 42, 42), "Café")

    // Un mapa para acceder fácilmente al color por nombre de clase
    val colorMap: Map<String, Pair<Int, String>> = mapOf(
        "paper" to paper,
        "cardboard" to cardboard,
        "plastic" to plastic,
        "metal" to metal,
        "glass" to glass,
        "trash" to trash,
        "compostable" to compostable
    )

    // Función para obtener el color de la bbox por clase, con un color por defecto
    fun getColorForClass(className: String): Int {
        return colorMap[className.lowercase()]?.first
            ?: Color.MAGENTA // Color por defecto si la clase no se encuentra
    }

    // Opcional: Función para obtener el nombre del color del basurero
    fun getBinColorNameForClass(className: String): String {
        return colorMap[className.lowercase()]?.second ?: "Desconocido"
    }
}