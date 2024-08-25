package net.torosamy.torosamyScript.commands


import net.torosamy.torosamyScript.utils.ListenerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.manager.BlockScriptManager
import net.torosamy.torosamyScript.manager.CommandGroupManager
import net.torosamy.torosamyScript.utils.CommandUtil
import net.torosamy.torosamyScript.utils.ConfigUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotations.Argument
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class Commands {
    @Command(value = "ts reload")
    @Permission("torosamyScript.reload")
    @CommandDescription("重载TorosamyScript配置文件")
    fun reloadConfig(sender: CommandSender) {
        ConfigUtil.reloadConfig()
        ListenerUtil.registerListener()
        CommandGroupManager.loadCommandGroups()
        BlockScriptManager.loadBlockScript()

        sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().reloadMessage))
    }

    @Command(value = "ts run <group> <player>")
    @Permission("torosamyScript.run")
    @CommandDescription("为在线玩家运行一个指令组")
    fun runGroup(
        sender: CommandSender,
        @Argument("player") player: Player,
        @Argument("group") commandGroupStr: String
    ) {


        val commandGroup: List<String> = CommandGroupManager.commandGroups[commandGroupStr] ?: return

        for (onlinePlayer in TorosamyScript.plugin.server.onlinePlayers) {
            if (onlinePlayer.name != player.name) continue
            for(command in commandGroup) {
                if(!CommandUtil.getCommand(onlinePlayer, command)) return
            }
            TorosamyScript.plugin.server.consoleSender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().runGroupSuccess).replace("{player}", player.name).replace("{group}", commandGroupStr))
            break
        }
    }
}