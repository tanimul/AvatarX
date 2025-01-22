package com.tanimul.avatarx.extentions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tanimul.avatarx.GenerateAvatar
import com.tanimul.avatarx.enum.ColorShade
import com.tanimul.avatarx.enum.Shape

@BindingAdapter("bindAvatar")
fun ImageView.bindAvatar(name: String) {
    Glide.with(context).load(
        GenerateAvatar.AvatarBuilder(context)
            .setLabel(name)
            .setAvatarSize(128)
            .setTextSize(30)
            .toSquare()
            .toCircle()
            .build()
    ).into(this)
}