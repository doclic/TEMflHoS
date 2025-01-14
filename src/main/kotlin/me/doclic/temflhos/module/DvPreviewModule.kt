package me.doclic.temflhos.module

import me.doclic.temflhos.event.EventHandler
import me.doclic.temflhos.event.KeyboardEvent
import me.doclic.temflhos.event.RenderItemOverlayPostEvent
import me.doclic.temflhos.util.drawOverlay
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.input.Keyboard

object DvPreviewModule : Module("dv_preview", "ID Preview", keyCode = Keyboard.KEY_P, resetOnDisconnect = false) {

    @EventHandler
    fun onRenderItemOverlayPostEvent(e: RenderItemOverlayPostEvent) {
        e.text = "XD"
    }
}