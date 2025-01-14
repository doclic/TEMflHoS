package me.doclic.temflhos.module

import me.doclic.temflhos.event.EventHandler
import me.doclic.temflhos.event.KeyboardEvent
import me.doclic.temflhos.event.Listener
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.network.FMLNetworkEvent
import org.lwjgl.input.Keyboard
import java.util.Collections

object ModuleManager : Listener {
    private val writableRegistry = HashMap<String, Module>()

    fun register(module: Module) {
        if(writableRegistry.containsValue(module)) return

        writableRegistry[module.id] = module
    }
    val registry: Map<String, Module>
        get() = Collections.unmodifiableMap(writableRegistry)

    fun isEnabled(module: String): Boolean {
        return (registry.keys.contains(module) && registry[module]!!.enabled.value)
    }

    @SubscribeEvent
    fun onClientDisconnected(e: FMLNetworkEvent.ClientDisconnectionFromServerEvent) {
        for (module in writableRegistry.values)
            if(module.resetOnDisconnect)
                module.enabled.value = module.enabledByDefault
    }

    @EventHandler
    fun onKeyboard(e: KeyboardEvent) {
        if (!e.down) return
        if (!Keyboard.isKeyDown(Keyboard.KEY_RMENU)) return
        for (module in writableRegistry.values) {
            if (module.key.value != e.keyCode) continue
            val new = !module.enabled.value
            module.enabled.value = new
            if (module.enabled.value == new) module.sendStateUpdateMsg()
        }
    }
}