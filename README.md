# AvatarX

### Features
* Generates avatars from text (e.g., initials from names).
* Supports circular and square shapes.
* Customizable background colors and text sizes.
* Works with popular image loading libraries (Glide, Picasso).
* No external dependencies.
  
![avataX](https://github.com/user-attachments/assets/81632eb7-d72d-45d5-9c6c-59a9da0806b4)

## Using AvatarX Library in your Android application

Add this in your `settings.gradle`:
```groovy
maven { url 'https://jitpack.io' }
```

If you are using `settings.gradle.kts`, add the following:
```kotlin
maven { setUrl("https://jitpack.io") }
```

Add this in your `build.gradle`
```groovy
implementation 'com.github.tanimul:AvatarX:1.0.1'
```

If you are using `build.gradle.kts`, add the following:
```kotlin
implementation("com.github.tanimul:AvatarX:1.0.1")
```

Initializing it with some customization:
```kotlin
AvatarGenerator.AvatarBuilder(context)
                .setLabel("TANIMUML ISLAM")
                .setAvatarSize(128)
                .setTextSize(24)
                .toCircle()
                .setBackgroundColor(Color.BLUE)
                .build()
```

### Using Glide or Picasso
You can use AvatarX as a placeholder in image loading libraries

Glide:
```kotlin
Glide.with(this)
    .load("https://example.com/image.jpg")
    .placeholder(AvatarGenerator.avatarImage(this, 128, AvatarConstants.SQUARE, "TI"))
    .into(imageView)        
```

Picasso:
```kotlin
Picasso.get()
    .load("https://example.com/image.jpg")
    .placeholder(AvatarGenerator.avatarImage(this, 128, AvatarConstants.CIRCLE, "TI"))
    .into(imageView)          
```

### Without Any Library
Set the generated drawable manually:
```kotlin
imageView.setImageDrawable(
    AvatarGenerator.avatarImage(
        this,
        128,
        AvatarConstants.CIRCLE,
        "AvatarX"
    )
)
```

### Shapes Supported
Set the generated drawable manually

Circle:
```kotlin
.toCircle()
```

Square:
```kotlin
.toSquare()
```

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

### Contributing to AvatarX
All pull requests are welcome, make sure to follow the [contribution guidelines](CONTRIBUTING.md)
when you submit pull request.
