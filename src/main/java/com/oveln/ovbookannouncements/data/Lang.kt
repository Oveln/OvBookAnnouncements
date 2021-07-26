package com.oveln.ovbookannouncements.data

import com.oveln.ovbookannouncements.Main
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Lang {
    private val path = "plugins/${Main.Instance.description.name}/${Config.lang}.yml"
    lateinit var lang : YamlConfiguration
    fun load() {
        var file : File
        file = File("/plugins/${Main.Instance.name}/zh_cn.yml")
        if (!file.exists()) {
            Main.Instance.saveResource("zh_cn.yml" , false)
        }
        file = File(path)
        if (!file.exists()) {
            Main.Instance.logger.warning("语言文件丢失")
            file = File("/plugins/${Main.Instance.name}/zh_cn.yml")
        }
        lang = YamlConfiguration()
        lang.load(file)
    }
}