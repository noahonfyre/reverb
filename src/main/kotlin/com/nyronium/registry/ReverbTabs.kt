package com.nyronium.registry

import com.nyronium.Reverb
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object ReverbTabs {
    val TAB_KEY: ResourceKey<CreativeModeTab> = ResourceKey.create(
        BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Reverb.ID, "reverb_tab")
    )

    val TAB: CreativeModeTab = FabricCreativeModeTab.builder()
        .icon { ItemStack(ReverbItems.ECHO_CHARGE) }
        .title(Component.translatable("itemGroup.reverb"))
        .displayItems { params, output ->
            for (item in ReverbItems.REGISTERED_ITEMS) {
                output.accept(item)
            }
        }
        .build()

    fun initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_KEY, TAB)
    }
}