package net.torosamy.torosamyScript.scheduler

import net.torosamy.torosamyScript.pojo.CommandGroup
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TimerTask : BukkitRunnable {
    val ticks: Int

    val commandGroup: CommandGroup
    
    val target: Player
    
    var counts: Int = 0
    
    public constructor(player: Player, commandGroup: CommandGroup, seconds: Int) {
        this.ticks = seconds * 20
        this.commandGroup = commandGroup
        this.target = player
    }
    
    override fun run() {
        if (!target.isOnline || counts >= ticks) {
            this.cancel()
            
            return
        }

        commandGroup.runCommands(target)
        
        counts++
    }
}