package me.doclic.temflhos.util

import net.minecraft.item.ItemStack

fun getFullSkyblockID(itemStack: ItemStack?): String? {
    return itemStack?.tagCompound?.getCompoundTag("ExtraAttributes")?.getString("id")
}

fun getShortSkyblockID(itemStack: ItemStack?): String? {
    return getFullSkyblockID(itemStack)?.split(":")?.get(0)
}

fun getDamageFromSkyblockID(itemStack: ItemStack?): Int {
    val sbid = getFullSkyblockID(itemStack)
    if (sbid == null || sbid.split(":").size == 1) return 0
    return sbid.split(":")[1].toInt()
}