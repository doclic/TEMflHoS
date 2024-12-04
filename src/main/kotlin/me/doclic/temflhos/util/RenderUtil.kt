package me.doclic.temflhos.util

import me.doclic.temflhos.event.RenderItemOverlayPostEvent
import net.minecraft.client.renderer.GlStateManager

fun drawOverlay(e: RenderItemOverlayPostEvent) {
    //TODO: finish
    GlStateManager.disableLighting()
    GlStateManager.disableDepth()
    GlStateManager.disableBlend()
    e.fr.drawStringWithShadow(
        e.text,
        (e.xPosition + 17 - e.fr.getStringWidth(e.text)).toFloat(),
        (event.y + 9).toFloat(),
        16777215
    )
    GlStateManager.enableLighting()
    GlStateManager.enableDepth()
}