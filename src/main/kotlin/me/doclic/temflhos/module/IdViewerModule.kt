package me.doclic.temflhos.module

import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object IdViewerModule : Module("id_viewer", "SkyBlock ID Viewer") {
    @SubscribeEvent
    fun editTooltip(e: ItemTooltipEvent) {
        if (!e.showAdvancedItemTooltips) return
        val sbId = e.itemStack?.tagCompound?.getCompoundTag("ExtraAttributes")?.getString("id") ?: return
        for (i in e.toolTip.lastIndex downTo 0) {
            if (!e.toolTip[i].startsWith("${EnumChatFormatting.DARK_GRAY}minecraft:")) continue
            e.toolTip.add(i + 1, "${EnumChatFormatting.DARK_GRAY}skyblock:${sbId}")
            return
        }
        e.toolTip.add("${EnumChatFormatting.DARK_GRAY}skyblock:${sbId}")
    }
}