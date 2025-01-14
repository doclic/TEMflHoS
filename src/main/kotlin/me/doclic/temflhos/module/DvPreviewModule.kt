package me.doclic.temflhos.module

import me.doclic.temflhos.config.ConfigNode
import me.doclic.temflhos.config.ListConfigType
import me.doclic.temflhos.config.StringConfigType
import me.doclic.temflhos.event.EventHandler
import me.doclic.temflhos.event.RenderItemOverlayPostEvent
import me.doclic.temflhos.util.getDamageFromSkyblockID
import me.doclic.temflhos.util.getShortSkyblockID
import net.minecraft.item.ItemStack
import org.lwjgl.input.Keyboard

object DvPreviewModule : Module("dv_preview", "ID Preview", keyCode = Keyboard.KEY_P, resetOnDisconnect = false) {
    private val showDvIDList = ConfigNode("showDvIDList", listOf("FISHING_ROD", "LEATHER_BOOTS", "MAP"), ListConfigType(StringConfigType), config)

    @EventHandler
    fun onRenderItemOverlayPostEvent(e: RenderItemOverlayPostEvent) {
        var itemStack: ItemStack = e.stack
        e.text = getDamageFromSkyblockID(itemStack).toString()
        e.cancelled = !showDvIDList.value.contains(getShortSkyblockID(itemStack))
    }
}