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

    fun getColor(): Int {
        if (colors.isEmpty()) {
            while (recycle.isNotEmpty()) colors.push(recycle.pop())
            Collections.shuffle(colors)
        }
        val color = colors.pop()
        recycle.push(color)
        return color
    }

}