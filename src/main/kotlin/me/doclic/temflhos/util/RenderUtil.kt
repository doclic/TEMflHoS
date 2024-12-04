package me.doclic.temflhos.util

import me.doclic.temflhos.event.RenderItemOverlayPostEvent
import net.minecraft.client.renderer.GlStateManager

fun drawOverlay(e: RenderItemOverlayPostEvent) {
    //TODO: finish
    if (e.text.isEmpty()) return
    GlStateManager.disableLighting()
    GlStateManager.disableDepth()
    GlStateManager.disableBlend()
    e.fr.drawStringWithShadow(
        e.text,
        (e.xPosition + 17 - e.fr.getStringWidth(e.text)).toFloat(),
        (e.yPosition + 9).toFloat(),
        16777215
    )
    GlStateManager.enableLighting()
    GlStateManager.enableDepth()
}