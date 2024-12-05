package me.doclic.temflhos.event

import me.doclic.temflhos.util.eventBus
import me.doclic.temflhos.util.mc
import java.lang.reflect.Method
import java.util.Collections
import java.util.concurrent.atomic.AtomicBoolean

object ListenerManager {
    private val writableRegistry = HashMap<Listener, Set<Method>>()
    private val queue = ArrayList<Event>()
    private var waitingForDispatch = AtomicBoolean(false)

    fun register(listener: Listener) {
        if(writableRegistry.contains(listener)) return

        val methods = HashSet<Method>()
        for (method in listener.javaClass.declaredMethods) {
            for (annotation in method.declaredAnnotations) {
                if (!EventHandler::class.java.isAssignableFrom(annotation.annotationClass.java)) continue
                if (method.returnType != Void.TYPE) break
                if (method.parameterCount != 1) break
                if (!Event::class.java.isAssignableFrom(method.parameters[0].type)) break
                method.isAccessible = true
                methods.add(method)
                break
            }
        }

        writableRegistry[listener] = Collections.unmodifiableSet(methods)
        eventBus.register(listener)
    }
    fun unregister(listener: Listener) {
        writableRegistry.remove(listener)
        eventBus.unregister(listener)
    }
    fun queue(e: Event) = synchronized(queue) {
        queue.add(e)
    }
    fun dispatchQueue() = synchronized(queue) {
        for (e in queue) dispatch(e)
        queue.clear()
        waitingForDispatch.set(false)
    }
    fun waitForDispatch() {
        if (mc.isCallingFromMinecraftThread) {
            dispatchQueue()
            return
        }
        if (queue.size == 0) return
        waitingForDispatch.set(true)
        while (waitingForDispatch.get()) {}
    }
    private fun dispatch(e: Event) {
        // we copy in case event handlers register/unregister listeners
        for (listener in HashMap<Listener, Set<Method>>(writableRegistry)) {
            for (method in listener.value) {
                if (!e.javaClass.isAssignableFrom(method.parameters[0].type)) continue
                method.invoke(listener.key, e)
            }
        }
    }
}