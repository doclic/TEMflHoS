package me.doclic.temflhos

import me.doclic.temflhos.command.*
import me.doclic.temflhos.config.ConfigIO
import me.doclic.temflhos.event.Listener
import me.doclic.temflhos.event.ListenerManager
import me.doclic.temflhos.event.dispatcher.PacketEventDispatcher
import me.doclic.temflhos.module.*
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

@Mod(name = TEMflHoS.MODNAME, modid = TEMflHoS.MODID, clientSideOnly = true, acceptedMinecraftVersions = "1.8.9")
class TEMflHoS : Listener {
    companion object {
        const val MODNAME = "TEMflHoS"
        const val MODID = "temflhos"
        val name: String
            get() {
                if (mwhpakowbnmtu) return "${MODNAME}//MWHPaKoWBnMtU"
                return MODNAME
            }
        // Mode Which Hopefully Prevents any Kind of Watchdog Bans no Matter the Use
        var mwhpakowbnmtu = true
            private set
    }

    @EventHandler
    fun onInit(e: FMLInitializationEvent) {
        if (System.getProperty("TEMflHoS.MWHPaKoWBnMtU", "0") == "0") {
            mwhpakowbnmtu = false
            println("TEMflHoS MWHPaKoWBnMtU disabled")
        }

        ClientCommandHandler.instance.registerCommand(ModuleCommand)
        ClientCommandHandler.instance.registerCommand(ConfigCommand)

        if (!mwhpakowbnmtu) {
            ModuleManager.register(PacketDisablerModule)
            ModuleManager.register(PacketFirewallModule)
            ModuleManager.register(GhostItemModule)
            ModuleManager.register(SaveGUIModule)
            ModuleManager.register(SafeInteractModule)
        }
        ModuleManager.register(HudModule)
        ModuleManager.register(MainMenuModule)
        ModuleManager.register(CrashPatchModule) // technically detectable but who cares
        ModuleManager.register(PacketLoggerModule)
        ModuleManager.register(FullBrightModule)
        ModuleManager.register(BetterTooltipsModule)
        ModuleManager.register(SuggestCommandsModule)
        ModuleManager.register(DvPreviewModule)

        ListenerManager.register(this)
        ListenerManager.register(ModuleManager)
        ListenerManager.register(PacketEventDispatcher)

        ConfigIO.reloadConfig()
    }

    @SubscribeEvent
    fun onTick(e: TickEvent) {
        ListenerManager.dispatchQueue()
    }
}