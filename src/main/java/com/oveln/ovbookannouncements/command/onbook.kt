package com.oveln.ovbookannouncements.command

import com.oveln.ovbookannouncements.data.verifier
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class onbook : CommandExecutor {
    override fun onCommand(commandsender: CommandSender, cmd: Command, flg: String, args: Array<out String>): Boolean {
        if (commandsender.isOp && args.size == 0 && commandsender is Player) {
            verifier.OpenBook(commandsender)
            return true
        }
        if (commandsender.isOp() && args.size == 1 && args[0] == "reset") {
            verifier.verified.clear()
            return true
        }
        return false
    }
}