package net.torosamy.torosamyScript.manager

import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.TorosamyScript
import org.bukkit.configuration.ConfigurationSection


class CommandGroupManager {
    companion object {
        val commandGroups = HashMap<String,List<String>>()


        fun loadCommandGroups() {
            commandGroups.clear()
            //一个配置文件可能有多个commandGroup
            loadCommandGroupData(ConfigManager.loadYamls(TorosamyScript.plugin,"scripts",""))
            TorosamyScript.plugin.server.consoleSender.sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyScript &a成功加载 &e${commandGroups.size} &a个指令组喵~"))
        }


        private fun loadCommandGroupData(commandGroupYmls: HashMap<String, ConfigurationSection>) {
            for (commandGroupYml in commandGroupYmls.values) {
                for (commandGroupString in commandGroupYml.getKeys(false)) {
                    commandGroups[commandGroupString] = commandGroupYml.getStringList(commandGroupString)
                }
            }
        }
    }
}