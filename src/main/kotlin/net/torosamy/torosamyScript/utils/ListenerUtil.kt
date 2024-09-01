package net.torosamy.torosamyScript.utils

import net.torosamy.torosamyScript.TorosamyScript

import net.torosamy.torosamyScript.listener.JoinEventListener
import net.torosamy.torosamyScript.listener.QuitEventListener

import org.bukkit.event.HandlerList
import org.bukkit.event.Listener


class ListenerUtil {
    companion object {
        private val joinEventListener:Listener = JoinEventListener()
        private val quitEventListener:Listener = QuitEventListener()

        fun registerListener() {
            registerJoinEventListener()
            registerQuitEventListener()

        }
        fun registerJoinEventListener() {
            HandlerList.unregisterAll(joinEventListener)
            if(!ConfigUtil.mainConfig.joinEvent.enabled) return
            TorosamyScript.plugin.server.pluginManager.registerEvents(joinEventListener, TorosamyScript.plugin)
        }
        fun registerQuitEventListener() {
            HandlerList.unregisterAll(quitEventListener)
            if(!ConfigUtil.mainConfig.quitEvent.enabled)  return
            else TorosamyScript.plugin.server.pluginManager.registerEvents(quitEventListener, TorosamyScript.plugin)
        }

    }
}