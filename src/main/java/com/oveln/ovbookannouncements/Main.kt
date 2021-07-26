package com.oveln.ovbookannouncements

import com.oveln.ovbookannouncements.command.onbook
import com.oveln.ovbookannouncements.data.Config
import com.oveln.ovbookannouncements.data.Lang
import com.oveln.ovbookannouncements.data.verifier
import com.oveln.ovbookannouncements.listener.onPlayerChat
import com.oveln.ovbookannouncements.listener.onPlayerMove
import com.oveln.ovbookannouncements.utils.CharUtils.colorful
import org.bukkit.plugin.java.JavaPlugin

class Main :JavaPlugin(){
    companion object {
        lateinit var Instance : Main
    }
    override fun onEnable() {
        Instance = this

        saveDefaultConfig()
        Config.load()
        Lang.load()
        verifier.load()

        server.pluginManager.run {
            registerEvents(onPlayerChat() , this@Main)
            registerEvents(onPlayerMove() , this@Main)
        }
        getCommand("book").executor = onbook()

        logger.info("&6${description.name}&f ${description.version}&2 启动成功   &c作者${description.authors}".colorful())

    }
    override fun onDisable() {
        verifier.save()
        logger.info("&6${description.name}&f ${description.version}&2 关闭成功   &c作者${description.authors}".colorful())
    }
}