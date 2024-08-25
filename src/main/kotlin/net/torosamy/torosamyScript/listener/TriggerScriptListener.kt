package net.torosamy.torosamyScript.listener

import net.torosamy.torosamyScript.manager.BlockScriptManager
import net.torosamy.torosamyScript.type.TriggerType
import net.torosamy.torosamyScript.utils.CommandUtil
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK
import org.bukkit.event.block.Action.LEFT_CLICK_BLOCK
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.EquipmentSlot
import kotlin.math.abs

class TriggerScriptListener : Listener {
    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val action = event.action

        if (event.hand != EquipmentSlot.HAND) return

        when (action) {
            RIGHT_CLICK_BLOCK -> {
                for (blockScript in BlockScriptManager.blockScripts.values) {
                    if (blockScript.location.world == event.player.world && blockScript.type == TriggerType.RIGHT_CLICK) {

                        val clickedBlock = event.clickedBlock ?: return
                        var clickX = clickedBlock.location.x
                        var clickZ = clickedBlock.location.z
                        if (clickX < 0) clickX++
                        if (clickZ < 0) clickZ++
                        if (clickX != blockScript.location.x ||
                            clickZ != blockScript.location.z
                        ) continue


                        for (str in blockScript.script) {
                            if (!CommandUtil.getCommand(event.player, str)) {
                                return
                            }
                        }
                    }
                }
            }

            LEFT_CLICK_BLOCK -> {
                for (blockScript in BlockScriptManager.blockScripts.values) {
                    if (blockScript.location.world == event.player.world && blockScript.type == TriggerType.LEFT_CLICK) {


                        val clickedBlock = event.clickedBlock ?: return
                        var clickX = clickedBlock.location.x
                        var clickZ = clickedBlock.location.z
                        if (clickX < 0) clickX++
                        if (clickZ < 0) clickZ++
                        if (clickX != blockScript.location.x ||
                            clickZ != blockScript.location.z
                        ) continue


                        for (str in blockScript.script) {
                            if (!CommandUtil.getCommand(event.player, str)) {
                                return
                            }
                        }
                    }
                }
            }

            else -> return
        }
    }

    @EventHandler
    fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        for (blockScript in BlockScriptManager.blockScripts.values) {
            if (blockScript.location.world == event.player.world && blockScript.type == TriggerType.WALK) {

                if (abs(event.to.x - blockScript.location.x) > 0.15 ||
                    abs(event.to.z - blockScript.location.z) > 0.15
                ) continue

                for (str in blockScript.script) {
                    if (!CommandUtil.getCommand(event.player, str)) {
                        return
                    }
                }
            }
        }
    }


}