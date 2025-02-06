package com.tanimul.avatarx

import com.tanimul.avatarx.enum.ColorShade
import java.util.Collections
import java.util.Stack

class RandomColors(colorShade: ColorShade = ColorShade.MEDIUM) {
    private val recycle: Stack<Int> = Stack()
    private val colors: Stack<Int> = Stack()

    init {
        recycle.addAll(colorShade.model.colors)
    }

    // Get a random color from the list of colors
    fun getColor(): Int {
        if (colors.isEmpty()) {
            while (recycle.isNotEmpty()) colors.push(recycle.pop())
            Collections.shuffle(colors)
        }

        val color = colors.pop()
        recycle.push(color)
        return color
    }

    // Get the color based on the first character of the name
    fun getColorForName(name: String): Int {
        val firstChar = name.firstOrNull()?.uppercaseChar() ?: return getColor()
        val index = firstChar - 'A'

        return if (index in 0 until recycle.size) recycle[index] else getColor()
    }
}
