package net.torosamy.torosamyScript.listener

import net.torosamy.torosamyScript.api.TorosamyScriptAPI
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action.LEFT_CLICK_BLOCK
import org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.EquipmentSlot
import kotlin.math.abs

class TriggerScriptListener : Listener {
    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (event.hand != EquipmentSlot.HAND) return
        
        val clickedBlock = event.clickedBlock ?: return
        
        val blockLocation = clickedBlock.location
        
        if (TorosamyScriptAPI.showClickLocation && event.player.isOp) {
            Bukkit.getConsoleSender().sendMessage(blockLocation.x.toInt().toString() + ", " +blockLocation.y.toInt() + ", " + blockLocation.z.toInt())
        }
        
        val action = event.action
        
        val scripts = when (action) {
            RIGHT_CLICK_BLOCK -> {
                TorosamyScriptAPI.rightBlockScripts
            }
            LEFT_CLICK_BLOCK -> {
                TorosamyScriptAPI.leftBlockScripts
            }
            else -> {
                return
            }
        }
        
        for (it in scripts.values) {
            if (it.location.world.name != blockLocation.world.name) {
                continue
            }
            
            if (it.location.x.toInt() != blockLocation.x.toInt()) {
                continue
            }
            
            if (it.location.y.toInt() != blockLocation.y.toInt()) {
                continue
            }

            if (it.location.z.toInt() != blockLocation.z.toInt()) {
                continue
            }

            it.commandGroup.runCommands(event.player)

            break 
        }
    }

    @EventHandler
    fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        val player = event.player
        
        for (it in TorosamyScriptAPI.walkBlockScripts.values) {
            if (it.location.world.name != player.world.name) {
                continue
            }
            
            if (abs(event.to.x - it.location.x) > 0.15) {
                continue
            }

            if (abs(event.to.z - it.location.z) > 0.15) {
                continue
            }
            
            it.commandGroup.runCommands(player)
            break
        }
    }
}