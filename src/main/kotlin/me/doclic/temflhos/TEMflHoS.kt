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

@Mod(name = TEMflHoS.NAME, modid = TEMflHoS.MODID, clientSideOnly = true, acceptedMinecraftVersions = "1.8.9")
class TEMflHoS : Listener {
    companion object {
        const val NAME = "TEMflHoS"
        const val MODID = "temflhos"
    }

    @EventHandler
    fun onInit(e: FMLInitializationEvent) {
        ClientCommandHandler.instance.registerCommand(ModuleCommand)
        ClientCommandHandler.instance.registerCommand(ConfigCommand)

        ModuleManager.register(PacketDisablerModule)
        ModuleManager.register(PacketFirewallModule)
        ModuleManager.register(HudModule)
        ModuleManager.register(MainMenuModule)
        ModuleManager.register(GhostItemModule)
        ModuleManager.register(SaveGUIModule)
        ModuleManager.register(CrashPatchModule)
        ModuleManager.register(SafeInteractModule)
        ModuleManager.register(PacketLoggerModule)
        ModuleManager.register(FullBrightModule)
        ModuleManager.register(BetterTooltipsModule)
        ModuleManager.register(SuggestCommandsModule)

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