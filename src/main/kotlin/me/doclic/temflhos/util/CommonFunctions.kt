package me.doclic.temflhos.util

import me.doclic.temflhos.TEMflHoS
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting

fun tChat(msg: String) {
    chat("${EnumChatFormatting.LIGHT_PURPLE}${EnumChatFormatting.BOLD}${TEMflHoS.NAME}${EnumChatFormatting.WHITE} >>${EnumChatFormatting.DARK_PURPLE} $msg")
}
fun chat(msg: String) {
    mc.ingameGUI.chatGUI.printChatMessage(ChatComponentText(msg))
}
