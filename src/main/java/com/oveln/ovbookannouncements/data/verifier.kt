package com.oveln.ovbookannouncements.data

import com.oveln.ovbookannouncements.Main
import io.netty.buffer.Unpooled
import net.minecraft.server.v1_12_R1.PacketDataSerializer
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import java.io.*
import java.util.*
import kotlin.collections.HashMap

object verifier {
    private val path = "plugins/${Main.Instance.description.name}/data.bin"
    lateinit var verified : MutableSet<UUID>
    val code = HashMap<UUID , String>()
    fun load() {
        val file = File(path)
        if (!file.exists()) {
            file.createNewFile()
            verified = HashSet<UUID>()
            return
        }
        val input = ObjectInputStream(FileInputStream(path))
        verified = input.readObject() as HashSet<UUID>
        input.close()
    }
    fun save() {
        val output = ObjectOutputStream(FileOutputStream(path))
        output.writeObject(verified)
        output.flush()
        output.close()
    }
    fun MakeNewBook(player: UUID):ItemStack {
        val book = ItemStack(Material.WRITTEN_BOOK)
        val meta = book.itemMeta as BookMeta

        val RandomCode : String
        if (code.containsKey(player))
            RandomCode = code[player]!!
        else RandomCode  = (100..999).random().toString()
        code[player] = RandomCode

        Config.Book.forEach() {
            meta.addPage(it)
        }
        meta.addPage(RandomCode)

        book.itemMeta = meta
        return book
    }
    fun OpenBook(player: Player) {
        val book = MakeNewBook(player.uniqueId)
        val slot = player.inventory.heldItemSlot
        val old = player.inventory.getItem(slot)

        player.inventory.setItem(slot , book)

        val buf = Unpooled.buffer(256)
        buf.setByte(0,0)
        buf.writerIndex(1)

        val packet = PacketPlayOutCustomPayload("MC|BOpen" , PacketDataSerializer(buf))
        (player as CraftPlayer).handle.playerConnection.sendPacket(packet)

        player.inventory.setItem(slot , old)
    }
}