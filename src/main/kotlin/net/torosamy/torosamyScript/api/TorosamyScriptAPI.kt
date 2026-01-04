package net.torosamy.torosamyScript.api

import net.torosamy.torosamyCore.api.TorosamyCoreAPI
import net.torosamy.torosamyCore.config.ConfigFile
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.pojo.BlockScript
import net.torosamy.torosamyScript.pojo.CommandGroup
import net.torosamy.torosamyScript.utils.ConfigUtil
import org.bukkit.Bukkit

object TorosamyScriptAPI {
    public val leftBlockScripts = HashMap<String, BlockScript>()
    
    public val rightBlockScripts = HashMap<String, BlockScript>()
    
    public val walkBlockScripts = HashMap<String, BlockScript>()

    private val commandGroups = HashMap<String, CommandGroup>()
    
    public var showClickLocation = false
    
    public fun loadCommandGroup() {
        commandGroups.clear()
        
        TorosamyCoreAPI.getConfigs(TorosamyScript.plugin, listOf("scripts")).values.forEach {
            it.getKeys(false).forEach { name->
                val section = it.getConfigurationSection(name)
                if (section != null) {
                    commandGroups[name] = CommandGroup.generate(section)
                }
            }
        }

        Bukkit.getConsoleSender().sendMessage(MessageUtil.format(
            ConfigUtil.langConfig.loadCommandGroupMessage.replace("%amount%", commandGroups.size.toString())
        ))
    }
    
    public fun getCommandGroup(name: String): CommandGroup? {
        return commandGroups[name]
    }
    
    public fun getCommandGroupNames(): List<String> {
        return commandGroups.keys.stream().toList()
    }

    private fun loadLeftBlockScripts() {
        leftBlockScripts.clear()

        val leftClick = ConfigFile(TorosamyScript.plugin, "left-click.yml", listOf("trigger")).config

        leftClick.getKeys(false).forEach {
            val section = leftClick.getConfigurationSection(it)
            if (section != null) {
                val script = BlockScript.generate(section)
                if (script != null) {
                    leftBlockScripts[it] = script
                }
            }
        }
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format(
            ConfigUtil.langConfig.loadLeftMessage.replace("%amount%", leftBlockScripts.size.toString())
        ))
    }
    
    private fun loadRightBlockScripts() {
        rightBlockScripts.clear()

        val rightClick = ConfigFile(TorosamyScript.plugin, "right-click.yml", listOf("trigger")).config

        rightClick.getKeys(false).forEach {
            val section = rightClick.getConfigurationSection(it)
            if (section != null) {
                val script = BlockScript.generate(section)
                if (script != null) {
                    rightBlockScripts[it] = script
                }
            }
        }

        Bukkit.getConsoleSender().sendMessage(MessageUtil.format(
            ConfigUtil.langConfig.loadRightMessage.replace("%amount%", rightBlockScripts.size.toString())
        ))
    }
    
    private fun loadWalkScripts() {
        walkBlockScripts.clear()

        val walk = ConfigFile(TorosamyScript.plugin, "walk.yml", listOf("trigger")).config

        walk.getKeys(false).forEach {
            val section = walk.getConfigurationSection(it)
            if (section != null) {
                val script = BlockScript.generate(section)
                if (script != null) {
                    walkBlockScripts[it] = script
                }
            }
        }
        
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format(
            ConfigUtil.langConfig.loadWalkMessage.replace("%amount%", walkBlockScripts.size.toString())
        ))
    }
    
    public fun loadBlockScripts() {
        loadLeftBlockScripts()
        loadRightBlockScripts()
        loadWalkScripts()
    }
}