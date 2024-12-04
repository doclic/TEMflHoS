package me.doclic.temflhos.module

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.input.Keyboard

object DvPreviewModule : Module("dv_preview", "ID Preview", keyCode = Keyboard.KEY_P, resetOnDisconnect = false) {
    @SubscribeEvent
    fun onRenderItemOverlayPost (e: RenderOverlayEvent)
}