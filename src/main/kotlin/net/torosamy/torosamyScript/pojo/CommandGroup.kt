package net.torosamy.torosamyScript.pojo

import net.torosamy.torosamyCore.api.TorosamyCoreAPI
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player

open class CommandGroup {
    private val commands: List<String>

    private val denyCommands: Map<String, List<String>>
    
    private constructor(commands: List<String>, denyCommands: Map<String, List<String>>) {
        this.commands = commands
        this.denyCommands = denyCommands
    }

    public fun runCommands(player: Player) {
        TorosamyCoreAPI.runCommands(player, commands, denyCommands)
    }

    companion object {
        public fun generate(config: ConfigurationSection): CommandGroup {
            val denyCommands = HashMap<String, List<String>>()
            
            val section = config.getConfigurationSection("denyCommands")
            
            section?.getKeys(false)?.forEach {
                denyCommands[it] = section.getStringList(it)
            }

            return CommandGroup(config.getStringList("commands"), denyCommands)
        }
    }
}