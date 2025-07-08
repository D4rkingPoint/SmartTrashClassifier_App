package com.surendramaran.yolov8tflite // O tu paquete

import yolov8tflite.R // Importa R de tu módulo app (ej: com.example.appname.R)

object RecommendationData {

    // Mensajes de recomendación
    private val recommendations: Map<String, String> = mapOf(
        "paper" to "Deposita el papel limpio y seco en el contenedor azul. Evita reciclar papel sucio o con grasa.",
        "cardboard" to "Dobla el cartón para ahorrar espacio y colócalo en el contenedor azul. No debe tener restos de comida.",
        "plastic" to "Enjuaga los envases plásticos y colócalos en el contenedor amarillo. No mezcles con residuos orgánicos.",
        "metal" to "Latas y envases metálicos deben ir limpios al contenedor gris. Evita desechar objetos con restos de alimentos.",
        "glass" to "Bota botellas y frascos de vidrio en el contenedor verde. No incluyas vidrios rotos, espejos o cerámica.",
        //"trash" to "Este residuo va al contenedor gris o negro. No es reciclable, evita mezclarlo con otros materiales.",
        "compostable" to "Deposita residuos orgánicos como restos de comida o cáscaras en el contenedor café."
    )

    // Mapeo de nombre de clase a recurso drawable de la imagen del basurero
    // Asegúrate que los nombres de los drawables coincidan con tus archivos en res/drawable
    private val binImages: Map<String, Int> = mapOf(
        "paper" to R.drawable.tachos_azul, // Ejemplo: bin_azul.png
        "cardboard" to R.drawable.tachos_azul,
        "plastic" to R.drawable.tachos_amarillo, // Ejemplo: bin_amarillo.png
        "metal" to R.drawable.tachos_gris,     // Ejemplo: bin_gris.png
        "glass" to R.drawable.tachos_verde,    // Ejemplo: bin_verde.png
        //"trash" to R.drawable.tachos_negro,   // Ya tienes esta: tachos_negro.png
        "compostable" to R.drawable.tachos_cafe    // Ejemplo: bin_cafe.png
    )

    // Imagen y texto por defecto (cuando no hay detección o clase desconocida)
    val defaultBinImageResId: Int = R.drawable.tachos_negro
    val defaultRecommendation: String = "Apunta la cámara a un objeto para identificarlo y recibir una recomendación de reciclaje."


    fun getRecommendation(className: String?): String {
        return if (className != null) {
            recommendations[className.lowercase()] ?: defaultRecommendation
        } else {
            defaultRecommendation
        }
    }

    fun getBinImageResId(className: String?): Int {
        return if (className != null) {
            binImages[className.lowercase()] ?: defaultBinImageResId
        } else {
            defaultBinImageResId
        }
    }
}
