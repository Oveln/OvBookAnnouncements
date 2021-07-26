package com.oveln.ovbookannouncements.listener

import com.oveln.ovbookannouncements.data.verifier
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class onPlayerMove :Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onListened(event: PlayerMoveEvent):Boolean {
        if (!verifier.verified.contains(event.player.uniqueId) && !event.isCancelled) {
            if (event.from.x == event.to.x && event.from.z == event.to.z) return true
            verifier.OpenBook(event.player)
        }
        return true
    }
}