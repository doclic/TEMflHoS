package me.doclic.temflhos.event

import net.minecraft.client.gui.FontRenderer
import net.minecraft.item.ItemStack

class RenderItemOverlayPostEvent (var fontRenderer: FontRenderer, var stack: ItemStack, var xPosition: Int, var yPosition: Int, var text: String, var cancelled: Boolean = false): Event()