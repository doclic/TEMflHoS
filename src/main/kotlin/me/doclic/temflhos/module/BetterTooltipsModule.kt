package me.doclic.temflhos.module

import me.doclic.temflhos.config.BooleanConfigType
import me.doclic.temflhos.config.ConfigNode
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.input.Keyboard
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

object BetterTooltipsModule : Module("better_tooltips", "Better tooltips") {
    private val show_sb_id = ConfigNode("show_sb_id", true, BooleanConfigType, config)
    private val nbt_copy = ConfigNode("nbt_copy", true, BooleanConfigType, config)

    @SubscribeEvent
    fun editTooltip(e: ItemTooltipEvent) {
        if (show_sb_id.value && e.showAdvancedItemTooltips) {
            val sbId = e.itemStack?.tagCompound?.getCompoundTag("ExtraAttributes")?.getString("id")
            if (sbId != null) {
                var found = false
                for (i in e.toolTip.lastIndex downTo 0) {
                    if (!e.toolTip[i].startsWith("${EnumChatFormatting.DARK_GRAY}minecraft:")) continue
                    e.toolTip.add(i + 1, "${EnumChatFormatting.DARK_GRAY}skyblock:${sbId}")
                    found = true
                    break
                }
                if (!found) e.toolTip.add("${EnumChatFormatting.DARK_GRAY}skyblock:${sbId}")
            }
        }

        if (nbt_copy.value) {
            e.toolTip.add("${EnumChatFormatting.DARK_GRAY}Press RIGHT ALT+C to copy NBT")
            if (Keyboard.isKeyDown(Keyboard.KEY_RMENU) && Keyboard.isKeyDown(Keyboard.KEY_C)) {
                val nbtString = e.itemStack?.tagCompound?.toString()
                if (nbtString != null) {
                    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                    clipboard?.setContents(StringSelection(nbtString), null)
                }
            }
        }
    }
}