package com.mubashshir.lokal.data.mapper

import com.mubashshir.lokal.data.model.Image
import com.mubashshir.lokal.data.remote.dto.ImageDto

fun List<ImageDto>.toImage(): Image {
    return Image(
        low = firstOrNull { it.quality == "50x50" }?.link,
        medium = firstOrNull { it.quality == "150x150" }?.link,
        high = firstOrNull { it.quality == "500x500" }?.link
    )
}
