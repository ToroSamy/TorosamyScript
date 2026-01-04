package net.torosamy.torosamyScript.utils

import net.torosamy.torosamyScript.TorosamyScript
import net.torosamy.torosamyScript.listener.JoinEventListener
import net.torosamy.torosamyScript.listener.QuitEventListener
import net.torosamy.torosamyScript.listener.TriggerScriptListener


object ListenerUtil {
    private val joinEventListener = JoinEventListener()
    
    private val quitEventListener = QuitEventListener()
    
    private val triggerScriptListener = TriggerScriptListener()

    fun registerListener() {
        TorosamyScript.plugin.server.pluginManager.registerEvents(joinEventListener, TorosamyScript.plugin)

        TorosamyScript.plugin.server.pluginManager.registerEvents(quitEventListener, TorosamyScript.plugin)

        TorosamyScript.plugin.server.pluginManager.registerEvents(triggerScriptListener, TorosamyScript.plugin)
    }
}