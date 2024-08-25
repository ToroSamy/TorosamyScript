package net.torosamy.torosamyScript

import net.torosamy.torosamyScript.utils.ListenerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.listener.TriggerScriptListener
import net.torosamy.torosamyScript.manager.BlockScriptManager
import net.torosamy.torosamyScript.manager.CommandGroupManager
import net.torosamy.torosamyScript.utils.CommandUtil
import net.torosamy.torosamyScript.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TorosamyScript : JavaPlugin() {
    companion object{lateinit var plugin: TorosamyScript }

    override fun onEnable() {
        plugin = this

        ConfigUtil.reloadConfig()
        CommandUtil.registerCommand()
        ListenerUtil.registerListener()
        CommandGroupManager.loadCommandGroups()
        BlockScriptManager.loadBlockScript()
        server.pluginManager.registerEvents(TriggerScriptListener(), this)


        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyScript &a成功开启喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a作者 &eTorosamy|yweiyang"))
    }

    override fun onDisable() {
        ConfigUtil.saveConfig()

        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c插件 &eTorosamyScript &c成功关闭喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c作者 &eTorosamy|yweiyang"))
    }
}
