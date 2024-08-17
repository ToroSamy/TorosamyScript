package net.torosamy.torosamyJoin.utils

import net.torosamy.torosamyJoin.TorosamyJoin.Companion.plugin
import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyJoin.config.LangConfig
import net.torosamy.torosamyJoin.config.MainConfig

class ConfigUtil {
    companion object {
        private var mainConfig: MainConfig = MainConfig()
        private var mainConfigManager: ConfigManager = ConfigManager(mainConfig)
        private var langConfig: LangConfig = LangConfig()
        private var langConfigManager: ConfigManager = ConfigManager(langConfig)
        fun getMainConfig(): MainConfig {return mainConfig}
        fun getLangConfig(): LangConfig {return langConfig}

        fun reloadConfig() {
            mainConfigManager.load(plugin, "config.yml")
            langConfigManager.load(plugin, "lang.yml")
        }

        fun saveConfig() {
            mainConfigManager.saveFile()
            langConfigManager.saveFile()
        }
    }
}