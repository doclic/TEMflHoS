package me.doclic.temflhos.event

import net.minecraft.client.gui.FontRenderer
import net.minecraft.item.ItemStack

class RenderItemOverlayPostEvent (var fr: FontRenderer, var stack: ItemStack, var xPosition: Int, var yPosition: Int, var text: String)