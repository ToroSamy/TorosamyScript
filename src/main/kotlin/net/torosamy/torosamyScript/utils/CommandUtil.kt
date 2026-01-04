package net.torosamy.torosamyScript.utils


import net.torosamy.torosamyCore.commands.CommandManager
import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.commands.Commands

object CommandUtil {
    private val commanderManager: CommandManager = CommandManager(TorosamyScript.plugin)

    fun registerCommand() {
        commanderManager.annotationParser.parse(Commands())
    }
}