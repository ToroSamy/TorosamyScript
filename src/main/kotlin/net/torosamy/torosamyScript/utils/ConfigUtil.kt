package net.torosamy.torosamyScript.utils

import net.torosamy.torosamyCore.config.Config
import net.torosamy.torosamyCore.config.ConfigFile
import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.config.LangConfig
import net.torosamy.torosamyScript.config.MainConfig

object ConfigUtil {
    private val configs: ArrayList<Config> = ArrayList()

    public var mainConfig: MainConfig = MainConfig()
    public var langConfig: LangConfig = LangConfig()

    fun initConfig() {
        configs.clear()
        configs.add(Config(mainConfig, ConfigFile(TorosamyScript.plugin,"config.yml")))
        configs.add(Config(langConfig, ConfigFile(TorosamyScript.plugin,"lang.yml")))
    }

    fun reloadConfig() {
        for (config in configs) {
            config.load()
        }
    }

    fun saveConfig() {
        for (config in configs) {
            config.save()
        }
    }
}