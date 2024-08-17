package net.torosamy.beautifyMe.utils

import net.torosamy.torosamyJoin.TorosamyJoin
import net.torosamy.torosamyJoin.listener.FirstJoinListener
import net.torosamy.torosamyJoin.listener.JoinEventListener
import net.torosamy.torosamyJoin.listener.QuitEventListener
import net.torosamy.torosamyJoin.utils.ConfigUtil
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener


class ListenerUtil {
    companion object {
        private val joinEventListener:Listener = JoinEventListener()
        private val quitEventListener:Listener = QuitEventListener()
        private val firstJoinListener:Listener = FirstJoinListener()
        fun registerListener() {
            registerJoinEventListener()
            registerQuitEventListener()
            registerFirstJoinListener()
        }
        fun registerJoinEventListener() {
            HandlerList.unregisterAll(joinEventListener)
            if(!ConfigUtil.getMainConfig().joinEvent.enabled) return
            TorosamyJoin.plugin.server.pluginManager.registerEvents(joinEventListener,TorosamyJoin.plugin)
        }
        fun registerQuitEventListener() {
            HandlerList.unregisterAll(quitEventListener)
            if(!ConfigUtil.getMainConfig().quitEvent.enabled)  return
            else TorosamyJoin.plugin.server.pluginManager.registerEvents(quitEventListener,TorosamyJoin.plugin)
        }
        fun registerFirstJoinListener() {
            HandlerList.unregisterAll(firstJoinListener)
            if(!ConfigUtil.getMainConfig().firstJoin.enabled) return
            else TorosamyJoin.plugin.server.pluginManager.registerEvents(firstJoinListener,TorosamyJoin.plugin)
        }
    }
}