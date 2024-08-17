package net.torosamy.torosamyJoin

import net.torosamy.beautifyMe.utils.ListenerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyJoin.utils.CommandUtil
import net.torosamy.torosamyJoin.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TorosamyJoin : JavaPlugin() {
    companion object{lateinit var plugin: TorosamyJoin}

    override fun onEnable() {
        plugin = this

        ConfigUtil.reloadConfig()
        CommandUtil.registerCommand()
        ListenerUtil.registerListener()

        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyJoin &a成功开启喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a作者 &eTorosamy|yweiyang"))
    }

    override fun onDisable() {
        ConfigUtil.saveConfig()

        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c插件 &eTorosamyJoin &c成功关闭喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c作者 &eTorosamy|yweiyang"))
    }
}
