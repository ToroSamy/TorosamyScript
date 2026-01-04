package net.torosamy.torosamyScript.pojo

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.ConfigurationSection


class BlockScript{
    val commandGroup: CommandGroup
    
    val location: Location
    
    private constructor(commandGroup: CommandGroup, location: Location){
        this.location = location
        this.commandGroup = commandGroup
    }
    
    companion object {
        public fun generate(config: ConfigurationSection): BlockScript? {
            val worldName = config.getString("world", null) ?: return null

            val world = Bukkit.getWorld(worldName) ?: return null

            val x = config.getDouble("x", 0.0)
            val y = config.getDouble("y", 0.0)
            val z = config.getDouble("z", 0.0)

            val location = Location(world, x, y, z)

            return BlockScript(CommandGroup.generate(config), location)
        }
    }
}