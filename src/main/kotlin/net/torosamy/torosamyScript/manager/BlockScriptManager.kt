package net.torosamy.torosamyScript.manager

import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.pojo.BlockScript
import net.torosamy.torosamyScript.type.TriggerType
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration

class BlockScriptManager {
    companion object {
        val blockScripts = HashMap<String, BlockScript>()


        fun loadBlockScript() {
            blockScripts.clear()
            loadBlockScriptData(ConfigManager.loadYaml(TorosamyScript.plugin, "trigger", "left-click.yml"), TriggerType.LEFT_CLICK)

            TorosamyScript.plugin.server.consoleSender.sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyScript &a成功加载 &e${blockScripts.size} &a个左键类型脚本方块喵~"))
            var temp = blockScripts.size


            loadBlockScriptData(ConfigManager.loadYaml(TorosamyScript.plugin, "trigger", "right-click.yml"), TriggerType.RIGHT_CLICK)

            TorosamyScript.plugin.server.consoleSender.sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyScript &a成功加载 &e${blockScripts.size - temp} &a个右键类型脚本方块喵~"))
            temp = blockScripts.size


            loadBlockScriptData(ConfigManager.loadYaml(TorosamyScript.plugin, "trigger", "walk.yml"), TriggerType.WALK)
            TorosamyScript.plugin.server.consoleSender.sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyScript &a成功加载 &e${blockScripts.size - temp} &a个脚踩类型脚本方块喵~"))
        }

        private fun loadBlockScriptData(yml: YamlConfiguration, type: TriggerType) {
            for (blockScriptStr in yml.getKeys(false)) {
                val worldStr = yml.getString("${blockScriptStr}.world", "world") ?: continue

                val x = yml.getDouble("${blockScriptStr}.x", 0.0)
                val y = yml.getDouble("${blockScriptStr}.y", 0.0)
                val z = yml.getDouble("${blockScriptStr}.z", 0.0)

                val actions = yml.getStringList("${blockScriptStr}.actions")

                val blockScript =
                    BlockScript(
                        actions,
                        Location(Bukkit.getWorld(worldStr), x, y, z),
                        type
                    )

//                blockScript.cooldown = yml.getInt("${blockScriptStr}.cooldown", 0)
                val permission = yml.getString("${blockScriptStr}.permission")
                if (permission != null) {
                    blockScript.permission = permission
                }

                blockScripts[blockScriptStr] = blockScript
            }
        }
    }
}