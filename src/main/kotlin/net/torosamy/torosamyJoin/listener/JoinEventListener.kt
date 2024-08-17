package net.torosamy.torosamyJoin.listener

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyJoin.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinEventListener : Listener {
    @EventHandler
    fun playerOnJoin(event: PlayerJoinEvent) {
        if (!ConfigUtil.getMainConfig().joinEvent.enabled) return

        ConfigUtil.getMainConfig().joinEvent.actions.forEach { action: String ->
            if(action.startsWith("[console] ")) {
                val message = action.replace("[console] ", "")
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), MessageUtil.text(PlaceholderAPI.setPlaceholders(event.player,message)))
            }else if(action.startsWith("[player] ")) {
                val message = action.replace("[player] ", "")
                Bukkit.dispatchCommand(event.player, MessageUtil.text(PlaceholderAPI.setPlaceholders(event.player, message)))
            }else if(action.startsWith("[message] ")) {
                val message = action.replace("[message] ", "")
                event.player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(event.player,message)))
            }else if(action.startsWith("[allMessage] ")) {
                val message = action.replace("[allMessage] ", "")
                Bukkit.getOnlinePlayers().forEach{player: Player -> player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(event.player,message)))}
            }else{ Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&b[服务器娘]&cUnknown action: &e${action}")) }
        }

    }
}