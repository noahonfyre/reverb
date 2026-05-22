package com.nyronium.registry

import com.nyronium.Reverb
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier

object ReverbDataComponents {

    val STORED_LOCATION: DataComponentType<BlockPos> = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        Identifier.fromNamespaceAndPath(Reverb.ID, "stored_location"),
        DataComponentType.builder<BlockPos>().persistent(BlockPos.CODEC).build()
    )

    fun initialize() {}
}