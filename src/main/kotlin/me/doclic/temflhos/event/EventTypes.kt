package me.doclic.temflhos.event

open class Event {
    var dispatched: Boolean = false
        protected set
}
annotation class EventHandler()
interface Listener
