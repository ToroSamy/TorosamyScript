package net.torosamy.torosamyScript.manager

import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.TorosamyScript
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class CommandGroupManager {
    companion object {
        val commandGroupDirectory = File(TorosamyScript.plugin.dataFolder, "scripts")
        val commandGroups = HashMap<String,List<String>>()


        fun loadCommandGroups() {
            commandGroups.clear()
            //一个配置文件可能有多个commandGroup
            val commandGroupYmls = HashMap<String,ConfigurationSection>()
            loadCommandGroupConfigs(commandGroupDirectory, commandGroupYmls, "");
            loadCommandGroupData(commandGroupYmls)
            TorosamyScript.plugin.server.consoleSender.sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyScript &a成功加载 &e${commandGroups.size} &a个指令组喵~"))
        }


        private fun loadCommandGroupConfigs(scriptDirectory: File, commandGroupYmls: HashMap<String, ConfigurationSection>, path: String) {
            if(!scriptDirectory.exists()) scriptDirectory.mkdirs()
            for (file in scriptDirectory.listFiles()) {
                val filePath = if (path.isNotEmpty()) path + File.separator + file.name else file.name
                if (file.isDirectory) loadCommandGroupConfigs(file, commandGroupYmls, filePath)
                else if (file.name.endsWith(".yml")) commandGroupYmls[filePath] = YamlConfiguration.loadConfiguration(file)
            }
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