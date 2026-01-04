package net.torosamy.torosamyScript

import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyScript.api.TorosamyScriptAPI
import net.torosamy.torosamyScript.utils.CommandUtil
import net.torosamy.torosamyScript.utils.ConfigUtil
import net.torosamy.torosamyScript.utils.ListenerUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TorosamyScript : JavaPlugin() {
    companion object{lateinit var plugin: TorosamyScript }

    override fun onEnable() {
        plugin = this
        ConfigUtil.initConfig()
        ConfigUtil.reloadConfig()
        CommandUtil.registerCommand()
        ListenerUtil.registerListener()
        
        TorosamyScriptAPI.loadBlockScripts()
        TorosamyScriptAPI.loadCommandGroup()
        
        
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&a插件 &eTorosamyScript &a成功开启喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&a作者 &eTorosamy|yweiyang"))
    }

    override fun onDisable() {
        ConfigUtil.saveConfig()

        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&c插件 &eTorosamyScript &c成功关闭喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&c作者 &eTorosamy|yweiyang"))
    }
}
