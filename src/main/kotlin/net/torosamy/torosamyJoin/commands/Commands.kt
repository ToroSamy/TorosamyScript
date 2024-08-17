package net.torosamy.torosamyJoin.commands

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.beautifyMe.utils.ListenerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyJoin.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotations.Argument
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class Commands {
    @Command(value = "tj reload")
    @Permission("torosamyjoin.reload")
    @CommandDescription("重载TorosamyJoin配置文件")
    fun reloadConfig(sender: CommandSender) {
        ConfigUtil.reloadConfig()
        ListenerUtil.registerListener()
        sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().reloadMessage))
    }

    @Command(value = "tj title <main> <sub>")
    @Permission("torosamyjoin.title.all")
    @CommandDescription("向全服玩家发送big-title")
    fun sendTitleToAll(sender: CommandSender, @Argument("main") mainTitle: String, @Argument("sub") subTitle: String) {
        Bukkit.getOnlinePlayers().forEach{player: Player ->
            player.sendTitle(
                MessageUtil.text(PlaceholderAPI.setPlaceholders(player, mainTitle)),
                MessageUtil.text(PlaceholderAPI.setPlaceholders(player, subTitle))
            )
        }
    }

    @Command(value = "tj title <main> <sub> <player>")
    @Permission("torosamyjoin.title.single")
    @CommandDescription("向玩家单独发送big-title")
    fun sendTitleToSingle(sender: CommandSender, @Argument("main") mainTitle: String, @Argument("sub") subTitle: String,@Argument("player") player: Player) {
        player.sendTitle(
            MessageUtil.text(PlaceholderAPI.setPlaceholders(player, mainTitle)),
            MessageUtil.text(PlaceholderAPI.setPlaceholders(player, subTitle))
        )
    }
}