package com.surendramaran.yolov8tflite // Asegúrate que el package sea el correcto

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.text.color

// No necesitas importar R.color.bounding_box_color si los colores vienen de ObjectColors
// import androidx.core.content.ContextCompat
// import yolov8tflite.R // Comenta o elimina esta línea

class OverlayView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var results = listOf<BoundingBox>()
    private var boxPaint = Paint()
    private var textBackgroundPaint = Paint()
    private var textPaint = Paint()

    private var bounds = Rect()

    init {
        initPaints()
    }

    fun clear() {
        results = listOf()
        // No necesitas resetear los paints si los reconfiguras en initPaints o en draw
        invalidate()
        // initPaints() // Podrías llamar a initPaints aquí si es necesario resetear a un estado base
    }

    private fun initPaints() {
        textBackgroundPaint.color = Color.BLACK
        textBackgroundPaint.style = Paint.Style.FILL
        textBackgroundPaint.textSize = 50f

        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 50f

        // boxPaint se configurará dinámicamente en el método draw,
        // pero puedes establecer propiedades comunes aquí.
        boxPaint.strokeWidth = 8F
        boxPaint.style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        results.forEach { result -> // Cambié 'it' a 'result' para mayor claridad
            val left = result.x1 * width
            val top = result.y1 * height
            val right = result.x2 * width
            val bottom = result.y2 * height

            // *** INICIO DE LA MODIFICACIÓN ***
            // Obtener el color de la bbox basado en la clase del resultado
            val BboxColor = ObjectColors.getColorForClass(result.clsName)
            boxPaint.color = BboxColor // Aplicar el color al Paint de la bbox
            // *** FIN DE LA MODIFICACIÓN ***

            canvas.drawRect(left, top, right, bottom, boxPaint)
            val drawableText = result.clsName // El nombre de la clase ya lo tienes aquí

            // Opcional: Si quieres que el fondo del texto también cambie de color
            // podrías hacer algo como:
            // textBackgroundPaint.color = bboxColor // O un color contrastante
            // O si quieres mostrar el nombre del color del basurero:
            // val binColorName = ObjectColors.getBinColorNameForClass(result.clsName)
            // val displayText = "${result.clsName} (${binColorName})"

            textBackgroundPaint.getTextBounds(drawableText, 0, drawableText.length, bounds)
            val textWidth = bounds.width()
            val textHeight = bounds.height()

            canvas.drawRect(
                left,
                top,
                left + textWidth + BOUNDING_RECT_TEXT_PADDING,
                top + textHeight + BOUNDING_RECT_TEXT_PADDING,
                textBackgroundPaint
            )
            canvas.drawText(drawableText, left, top + bounds.height(), textPaint)
        }
    }

    fun setResults(boundingBoxes: List<BoundingBox>) {
        results = boundingBoxes
        invalidate() // Solicita que la vista se redibuje
    }

    companion object {
        private const val BOUNDING_RECT_TEXT_PADDING = 8
    }
}
