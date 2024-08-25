package net.torosamy.torosamyScript.pojo

import net.torosamy.torosamyScript.type.TriggerType
import org.bukkit.Location

class BlockScript(val script: List<String>, val location: Location,val type: TriggerType) {
    var permission: String = ""
}