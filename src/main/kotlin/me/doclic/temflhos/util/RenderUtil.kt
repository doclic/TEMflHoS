package me.doclic.temflhos.util

import me.doclic.temflhos.event.RenderItemOverlayPostEvent
import net.minecraft.client.renderer.GlStateManager

fun drawOverlay(e: RenderItemOverlayPostEvent, xOffset: Float = 0f, yOffset: Float = 0f, color: Int = 0x00FFDD) {
    if (e.text.isEmpty()) return
    GlStateManager.disableLighting()
    GlStateManager.disableDepth()
    GlStateManager.disableBlend()
    e.fontRenderer.drawStringWithShadow(
        e.text,
        (e.xPosition + xOffset).toFloat(),
        (e.yPosition + yOffset).toFloat(),
        color
    )
    GlStateManager.enableLighting()
    GlStateManager.enableDepth()
}

/**
 * if drawOverlay was so good
 * why didn't they make drawOverlay2()
 */
fun drawOverlay2(e: RenderItemOverlayPostEvent) {
    drawOverlay(e)
}
