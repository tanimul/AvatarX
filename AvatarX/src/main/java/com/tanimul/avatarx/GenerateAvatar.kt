package com.tanimul.avatarx

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.text.TextPaint
import com.tanimul.avatarx.enum.ColorShade
import com.tanimul.avatarx.enum.Shape

class GenerateAvatar(private val builder: AvatarBuilder) {

    class AvatarBuilder(private val context: Context) {
        private var textSize = 100
        private var size = 14
        private var name = " "
        private var backgroundColor: Int? = null
        private var shapeType = Shape.CIRCLE

        fun setTextSize(textSize: Int) = apply {
            this.textSize = textSize
        }

        fun setAvatarSize(size: Int) = apply {
            this.size = size
        }

        fun setLabel(label: String) = apply {
            this.name = label
        }

        fun setBackgroundColor(color: Int) = apply {
            this.backgroundColor = color
        }

        fun toSquare() = apply {
            this.shapeType = Shape.RECTANGLE
        }

        fun toCircle() = apply {
            this.shapeType = Shape.CIRCLE
        }

        fun build(): BitmapDrawable {
            return avatarImageGenerate(
                context, size, shapeType, name, textSize, ColorShade.MEDIUM
            )
        }

        private fun avatarImageGenerate(
            context: Context,
            size: Int,
            shape: Shape,
            name: String,
            textSize: Int,
            colorShade: ColorShade
        ): BitmapDrawable {
            val label = firstCharacter(name)
            val textPaint = textPainter(context, textSize)
            val painter = painter()
            painter.isAntiAlias = true
            val areaRect = Rect(0, 0, size, size)

            // Set background color based on shape
            painter.color = if (shape == Shape.RECTANGLE) Color.TRANSPARENT
            else backgroundColor ?: RandomColors(colorShade).getColor()

            val bitmap = Bitmap.createBitmap(size, size, ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawRect(areaRect, painter)

            val bounds = RectF(areaRect)
            bounds.right = textPaint.measureText(label)
            bounds.bottom = textPaint.descent() - textPaint.ascent()

            bounds.left += (areaRect.width() - bounds.right) / 2.0f
            bounds.top += (areaRect.height() - bounds.bottom) / 2.0f

            // Draw circle or square
            if (shape == Shape.CIRCLE) {
                canvas.drawCircle(
                    size.toFloat() / 2, size.toFloat() / 2, size.toFloat() / 2, painter
                )
            }

            // Draw text
            canvas.drawText(label, bounds.left, bounds.top - textPaint.ascent(), textPaint)

            return BitmapDrawable(context.resources, bitmap)
        }

        private fun firstCharacter(name: String): String {
            return name.firstOrNull()?.uppercaseChar()?.toString() ?: "-"
        }

        private fun textPainter(context: Context, textSize: Int): TextPaint {
            val textPaint = TextPaint()
            textPaint.isAntiAlias = true
            textPaint.textSize = textSize * context.resources.displayMetrics.scaledDensity
            textPaint.color = Color.WHITE
            return textPaint
        }

        private fun painter(): Paint = Paint()

    }

    companion object {
        fun avatarImage(
            context: Context, size: Int, shape: Shape, name: String, colorShade: ColorShade
        ): BitmapDrawable {
            return AvatarBuilder(context).apply {
                setAvatarSize(size)
                setLabel(name)
                toCircle()
            }.build()
        }
    }
}
