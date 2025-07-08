package com.surendramaran.yolov8tflite

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.text.color
import java.util.Locale // Para formatear el float

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
        invalidate()
    }

    private fun initPaints() {
        textBackgroundPaint.color = Color.BLACK
        textBackgroundPaint.style = Paint.Style.FILL
        textBackgroundPaint.textSize = 50f

        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 50f // Puedes ajustar el tamaño si el texto se vuelve muy largo

        boxPaint.strokeWidth = 8F
        boxPaint.style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        results.forEach { result ->
            val left = result.x1 * width
            val top = result.y1 * height
            val right = result.x2 * width
            val bottom = result.y2 * height

            val BboxColor = ObjectColors.getColorForClass(result.clsName)
            boxPaint.color = BboxColor

            canvas.drawRect(left, top, right, bottom, boxPaint)

            // *** MODIFICACIÓN PARA MOSTRAR LA CONFIANZA ***
            // Formatea la confianza a un porcentaje o a dos decimales
            // Opción 1: Como porcentaje (ej. 98%)
            // val confidenceText = String.format(Locale.US, "%d%%", (result.cnf * 100).toInt())
            // Opción 2: Como decimal (ej. 0.98)
            val confidenceText = String.format(Locale.US, "%.2f", result.cnf)

            val drawableText = "${result.clsName} ($confidenceText)" // Combina clase y confianza
            // *** FIN DE LA MODIFICACIÓN ***


            textBackgroundPaint.getTextBounds(drawableText, 0, drawableText.length, bounds)
            val textWidth = bounds.width()
            val textHeight = bounds.height()

            // Asegúrate de que el fondo del texto no se salga de la pantalla si está cerca del borde derecho
            val textBgRight = if (left + textWidth + BOUNDING_RECT_TEXT_PADDING > width) {
                width.toFloat()
            } else {
                left + textWidth + BOUNDING_RECT_TEXT_PADDING
            }

            // Asegúrate de que el fondo del texto no se salga de la pantalla si está cerca del borde inferior
            // (esto es menos común para el texto en la parte superior de la bbox)
            val textBgBottom = if (top + textHeight + BOUNDING_RECT_TEXT_PADDING > height) {
                height.toFloat()
            } else {
                top + textHeight + BOUNDING_RECT_TEXT_PADDING
            }


            canvas.drawRect(
                left,
                top,
                textBgRight, // Usa el valor ajustado
                top + textHeight + BOUNDING_RECT_TEXT_PADDING, // Podrías ajustar textBgBottom aquí también si es necesario
                textBackgroundPaint
            )
            canvas.drawText(drawableText, left, top + bounds.height(), textPaint)
        }
    }

    fun setResults(boundingBoxes: List<BoundingBox>) {
        results = boundingBoxes
        invalidate()
    }

    companion object {
        private const val BOUNDING_RECT_TEXT_PADDING = 8
    }
}
