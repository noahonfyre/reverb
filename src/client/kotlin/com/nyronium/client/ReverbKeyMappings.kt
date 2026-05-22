package com.nyronium.client

import com.mojang.blaze3d.platform.InputConstants
import com.nyronium.Reverb
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.minecraft.client.KeyMapping
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW

object ReverbKeyMappings {
    val CATEGORY: KeyMapping.Category = KeyMapping.Category.register(
        Identifier.fromNamespaceAndPath(Reverb.ID, "${Reverb.ID}_category")
    )

    val SONIC_STASIS: KeyMapping = KeyMappingHelper.registerKeyMapping(
        KeyMapping(
            "key.${Reverb.ID}.sonic_stasis",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_SPACE,
            CATEGORY
        )
    )
    val SONIC_DASH: KeyMapping = KeyMappingHelper.registerKeyMapping(
        KeyMapping(
            "key.${Reverb.ID}.sonic_dash",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            CATEGORY
        )
    )
    val SONIC_SLAM: KeyMapping = KeyMappingHelper.registerKeyMapping(
        KeyMapping(
            "key.${Reverb.ID}.sonic_slam",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_SHIFT,
            CATEGORY
        )
    )

    fun initialize() {}
}