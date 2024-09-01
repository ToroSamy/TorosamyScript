package net.torosamy.torosamyScript.utils

import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.config.LangConfig
import net.torosamy.torosamyScript.config.MainConfig

class ConfigUtil {
    companion object {
        var mainConfig: MainConfig = MainConfig()
        var langConfig: LangConfig = LangConfig()

        private var mainConfigManager: ConfigManager = ConfigManager(mainConfig,TorosamyScript.plugin,"", "config.yml")
        private var langConfigManager: ConfigManager = ConfigManager(langConfig,TorosamyScript.plugin,"", "lang.yml")


        fun reloadConfig() {
            mainConfigManager.load()
            langConfigManager.load()
        }

        fun saveConfig() {
            mainConfigManager.save()
            langConfigManager.save()
        }
    }
}