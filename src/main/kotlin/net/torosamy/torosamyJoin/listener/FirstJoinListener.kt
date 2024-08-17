package net.torosamy.torosamyJoin.listener

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyJoin.TorosamyJoin
import net.torosamy.torosamyJoin.scheduler.WelcomeRewardTask
import net.torosamy.torosamyJoin.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent

class FirstJoinListener : Listener {
    @EventHandler
    fun playerOnJoin(event: PlayerJoinEvent) {
        if(!ConfigUtil.getMainConfig().firstJoin.enabled) return
        //如果玩家游玩时长超过了500毫秒 则不认为是第一次进入服务器
        if(System.currentTimeMillis() - event.player.firstPlayed > 500) return

        ConfigUtil.getMainConfig().firstJoin.actions.forEach { action: String ->
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
                Bukkit.getOnlinePlayers().forEach{ player: Player -> player.sendMessage(MessageUtil.text(PlaceholderAPI.setPlaceholders(event.player,message)))}
            }else{ Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&b[服务器娘]&cUnknown action: &e${action}")) }
        }

        if(!ConfigUtil.getMainConfig().firstJoin.welcomeReward.enabled) return
        WelcomeRewardTask().runTaskTimerAsynchronously(TorosamyJoin.plugin,0,20L)
    }

    @EventHandler
    fun playerOnChat(event: AsyncPlayerChatEvent) {
        if(!ConfigUtil.getMainConfig().firstJoin.enabled) return
        if(!ConfigUtil.getMainConfig().firstJoin.welcomeReward.enabled) return
        //已经超时
        if(WelcomeRewardTask.duration <= 0) return
        ConfigUtil.getMainConfig().firstJoin.welcomeReward.keys.forEach {key: String ->
            //如果发送的信息不包含key
            if(!event.message.contains(key)) return
            //已经领取过一次奖励
            if(WelcomeRewardTask.hasBeenRewardList.contains(event.player.name)) return

            ConfigUtil.getMainConfig().firstJoin.welcomeReward.actions.forEach { action: String ->
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
}