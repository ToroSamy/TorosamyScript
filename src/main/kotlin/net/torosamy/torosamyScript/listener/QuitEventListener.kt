package net.torosamy.torosamyScript.listener


import net.torosamy.torosamyCore.api.TorosamyCoreAPI
import net.torosamy.torosamyScript.utils.CommandUtil
import net.torosamy.torosamyScript.utils.ConfigUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class QuitEventListener : Listener {
    @EventHandler
    fun playerOnQuit(event: PlayerQuitEvent) {
        if (!ConfigUtil.mainConfig.quitEvent.enabled) return

        TorosamyCoreAPI.runCommands(event.player, ConfigUtil.mainConfig.quitEvent.actions)
    }
}