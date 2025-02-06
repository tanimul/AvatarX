package com.tanimul.avatarx.extentions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tanimul.avatarx.GenerateAvatar
import com.tanimul.avatarx.enum.ColorShade

@BindingAdapter("bindAvatar")
fun ImageView.bindAvatar(name: String) {
    Glide.with(context).load(
        GenerateAvatar.AvatarBuilder(context)
            .setLabel(name)
            .setAvatarSize(128)
            .setTextSize(30)
            .useRandomColor(false)
            .setColorShade(ColorShade.MEDIUM)
            .toSquare()
            .toCircle()
            .build()
    ).into(this)
}