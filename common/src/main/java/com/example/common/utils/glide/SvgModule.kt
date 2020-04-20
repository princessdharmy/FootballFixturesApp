package com.example.common.utils.glide

import android.content.Context
import com.caverock.androidsvg.SVG
import android.graphics.drawable.PictureDrawable
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream


/**
 * Module for the SVG sample app.
 */
@GlideModule
class SvgModule : AppGlideModule() {
    override fun registerComponents(
        @NonNull context: Context, @NonNull glide: Glide,
        @NonNull registry: Registry
    ) {
        registry.register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
            .append(InputStream::class.java, SVG::class.java, SvgDecoder())
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}