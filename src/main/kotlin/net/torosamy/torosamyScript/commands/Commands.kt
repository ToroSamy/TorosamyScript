package net.torosamy.torosamyScript.commands


import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.api.TorosamyScriptAPI
import net.torosamy.torosamyScript.scheduler.TimerTask
import net.torosamy.torosamyScript.utils.ConfigUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotations.*
import org.incendo.cloud.annotations.suggestion.Suggestions
import org.incendo.cloud.context.CommandContext
import org.incendo.cloud.context.CommandInput

class Commands {
    @Command(value = "ts reload")
    @Permission("torosamyScript.reload")
    @CommandDescription("重载TorosamyScript配置文件")
    fun reloadConfig(sender: CommandSender) {
        ConfigUtil.reloadConfig()
        TorosamyScriptAPI.loadBlockScripts()
        TorosamyScriptAPI.loadCommandGroup()

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.reloadMessage))
    }

    @Command(value = "ts show-click")
    @Permission("torosamyScript.show-click")
    @CommandDescription("是否显示点击方块的位置")
    fun updateShowClick(sender: CommandSender) {
        TorosamyScriptAPI.showClickLocation = !TorosamyScriptAPI.showClickLocation
        
        sender.sendMessage(TorosamyScriptAPI.showClickLocation.toString())
    }


    @Command(value = "ts run <group> <player> [messaged] [tick] [seconds]")
    @Permission("torosamyScript.run")
    @CommandDescription("为在线玩家运行一个指令组")
    fun runGroup(sender: CommandSender, @Argument("player") player: Player, @Argument(value = "group", suggestions = "group") groupName: String, @Argument("tick") @Default("-1") tick: Int, @Argument("seconds") @Default("-1") seconds: Int, @Argument("messaged") @Default("true") messaged: Boolean) {
        val commandGroup = TorosamyScriptAPI.getCommandGroup(groupName) ?: return

        if (tick != -1 && seconds != -1) {
            TimerTask(player, commandGroup, seconds).runTaskTimer(TorosamyScript.plugin, 0L, 1L)
        }else {
            commandGroup.runCommands(player)
        }
        
        if (!messaged) {
            return
        }
        
        sender.sendMessage(
            MessageUtil.format(ConfigUtil.langConfig.runGroupSuccess)
                .replace("{player}", player.name)
                .replace("{group}", groupName)
        )
    }


    @Suggestions("group")
    fun groups(context: CommandContext<CommandSender>, input: CommandInput?): List<String> {

        return TorosamyScriptAPI.getCommandGroupNames();
    }
}