package me.doclic.temflhos.module

import me.doclic.temflhos.util.mc

object FullBrightModule : Module("fullbright", "FullBright") {
    override fun onEnable() {
        mc.gameSettings.gammaSetting = 100f
    }

    override fun onDisable() {
        mc.gameSettings.gammaSetting = 1f
    }
}