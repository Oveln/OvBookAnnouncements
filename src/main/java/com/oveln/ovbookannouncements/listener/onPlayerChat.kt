package com.oveln.ovbookannouncements.listener

import com.oveln.ovbookannouncements.data.Lang
import com.oveln.ovbookannouncements.data.verifier
import com.oveln.ovbookannouncements.utils.CharUtils.colorful
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class onPlayerChat :Listener {
    @EventHandler
    fun onListener(event: AsyncPlayerChatEvent):Boolean {
        if (verifier.code.containsKey(event.player.uniqueId)) {
            event.isCancelled = true
            if (event.message == verifier.code[event.player.uniqueId]) {
                verifier.code.remove(event.player.uniqueId)
                verifier.verified.add(event.player.uniqueId)
                event.player.sendMessage(Lang.lang.getString("SuccessMessage").colorful())
            } else {
                event.player.sendMessage(Lang.lang.getString("FailureMessage").colorful())
            }
        }
        return true
    }
}