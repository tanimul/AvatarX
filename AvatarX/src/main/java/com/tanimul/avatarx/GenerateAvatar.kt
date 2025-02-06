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
        private var useRandomColor = true
        private var colorShade: ColorShade = ColorShade.MEDIUM

        fun setTextSize(textSize: Int) = apply { this.textSize = textSize }
        fun setAvatarSize(size: Int) = apply { this.size = size }
        fun setLabel(label: String) = apply { this.name = label }
        fun setBackgroundColor(color: Int) = apply { this.backgroundColor = color }
        fun useRandomColor(useRandom: Boolean) = apply { this.useRandomColor = useRandom }
        fun setColorShade(shade: ColorShade) = apply { this.colorShade = shade }

        fun toSquare() = apply { this.shapeType = Shape.RECTANGLE }
        fun toCircle() = apply { this.shapeType = Shape.CIRCLE }

        fun build(): BitmapDrawable {
            return avatarImageGenerate(
                context,
                size,
                shapeType,
                name,
                textSize,
                colorShade,
                useRandomColor
            )
        }

        private fun avatarImageGenerate(
            context: Context,
            size: Int,
            shape: Shape,
            name: String,
            textSize: Int,
            colorShade: ColorShade,
            useRandomColor: Boolean
        ): BitmapDrawable {
            val label = firstCharacter(name)
            val textPaint = createTextPaint(context, textSize)
            val painter = Paint().apply { isAntiAlias = true }
            val areaRect = Rect(0, 0, size, size)

            // Handle background color based on user choice
            painter.color = if (shape == Shape.RECTANGLE) Color.TRANSPARENT
            else if (useRandomColor) RandomColors(colorShade).getColor()
            else backgroundColor ?: RandomColors(colorShade).getColorForName(name)

            val bitmap = Bitmap.createBitmap(size, size, ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawRect(areaRect, painter)

            val bounds = RectF(areaRect).apply {
                right = textPaint.measureText(label)
                bottom = textPaint.descent() - textPaint.ascent()
                left += (areaRect.width() - right) / 2.0f
                top += (areaRect.height() - bottom) / 2.0f
            }

            // Draw circle or square
            if (shape == Shape.CIRCLE) {
                canvas.drawCircle(size / 2f, size / 2f, size / 2f, painter)
            }

            // Draw text
            canvas.drawText(label, bounds.left, bounds.top - textPaint.ascent(), textPaint)

            return BitmapDrawable(context.resources, bitmap)
        }

        private fun firstCharacter(name: String): String {
            return name.firstOrNull()?.uppercaseChar()?.toString() ?: "-"
        }

        private fun createTextPaint(context: Context, textSize: Int): TextPaint {
            return TextPaint().apply {
                isAntiAlias = true
                this.textSize = textSize * context.resources.displayMetrics.density
                color = Color.WHITE
            }
        }
    }
}

