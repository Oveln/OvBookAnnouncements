package com.oveln.ovbookannouncements.utils

object CharUtils {
    fun String.colorful():String {
        return this.replace("&" , "§")
    }
}