package net.torosamy.torosamyJoin.utils

import net.torosamy.torosamyJoin.TorosamyJoin

import net.torosamy.torosamyCore.TorosamyCore
import net.torosamy.torosamyJoin.commands.Commands

class CommandUtil {
    companion object {
        private var torosamyCorePlugin: TorosamyCore = TorosamyJoin.plugin.server.pluginManager.getPlugin("TorosamyCore") as TorosamyCore
        fun registerCommand() {
            torosamyCorePlugin.getCommandManager().annotationParser.parse(Commands())
        }
    }
}