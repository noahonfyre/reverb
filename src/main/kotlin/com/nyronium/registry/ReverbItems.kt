package com.nyronium.registry

import com.nyronium.Reverb
import com.nyronium.content.item.EchoCharge
import com.nyronium.content.item.MagnetItem
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item


object ReverbItems {
    val REGISTERED_ITEMS = mutableListOf<Item>()

    val ECHO_CHARGE = register("echo_charge") { props -> EchoCharge(props) }
    val MAGNET = register("magnet") { props -> MagnetItem(props) }

    fun <T: Item> register(name: String, factory: (Item.Properties) -> T): T {
        val key: ResourceKey<Item> = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Reverb.ID, name))
        val item = Registry.register(BuiltInRegistries.ITEM, key, factory(Item.Properties().setId(key)))
        REGISTERED_ITEMS.add(item)
        return item
    }

    fun initialize() {}
}