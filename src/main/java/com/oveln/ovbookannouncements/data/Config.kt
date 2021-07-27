package com.oveln.ovbookannouncements.data

import com.oveln.ovbookannouncements.Main
import com.oveln.ovbookannouncements.utils.CharUtils.colorful


object Config {
    lateinit var lang : String
    lateinit var code : String
    lateinit var Book : MutableList<String>
    fun load() {
        val config = Main.Instance.config
        lang = config.getString("Language")?:"zh_cn"
        code = (config.getString("Code")?:"%code%").colorful()
        Book = ArrayList()
        var s = ""
        config.getStringList("Book").forEach() {
            s = if (it == "%page%") {
                Book.add(s)
                ""
            }else s+it.colorful()+"\n"
        }
        Book.add(s)
    }
}