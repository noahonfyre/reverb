package com.nyronium.registry

import com.mojang.serialization.Codec
import com.nyronium.Reverb
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.resources.Identifier

object ReverbAttachments {
    const val DEFAULT_SLAM_COOLDOWN_TICKS = 100
    const val DEFAULT_DASH_COOLDOWN_TICKS = 30
    const val DEFAULT_STASIS_CHARGES = 3
    const val DEFAULT_STASIS_COOLDOWN_TICKS = 15
    const val DEFAULT_AIRBORNE_GRACE_TICKS = 5

    val SLAM_COOLDOWN_TICKS: AttachmentType<Int> = AttachmentRegistry.create(
        Identifier.fromNamespaceAndPath(Reverb.ID, "slam_cooldown_ticks")
    ) { builder -> builder
        .initializer { DEFAULT_SLAM_COOLDOWN_TICKS }
        .persistent(Codec.INT)
    }

    val DASH_COOLDOWN_TICKS: AttachmentType<Int> = AttachmentRegistry.create(
        Identifier.fromNamespaceAndPath(Reverb.ID, "dash_cooldown_ticks")
    ) { builder -> builder
        .initializer { DEFAULT_DASH_COOLDOWN_TICKS }
        .persistent(Codec.INT)
    }
    val STASIS_CHARGES: AttachmentType<Int> = AttachmentRegistry.create(
        Identifier.fromNamespaceAndPath(Reverb.ID, "sonic_stasis_charges")
    ) { builder -> builder
        .initializer { DEFAULT_STASIS_CHARGES }
        .persistent(Codec.INT)
    }
    val STASIS_COOLDOWN_TICKS: AttachmentType<Int> = AttachmentRegistry.create(
        Identifier.fromNamespaceAndPath(Reverb.ID, "stasis_cooldown_ticks")
    ) { builder -> builder
        .initializer { DEFAULT_STASIS_COOLDOWN_TICKS }
        .persistent(Codec.INT)
    }
    val AIRBORNE_GRACE_TICKS: AttachmentType<Int> = AttachmentRegistry.create(
        Identifier.fromNamespaceAndPath(Reverb.ID, "airborne_grace_ticks")
    ) { builder -> builder
        .initializer { DEFAULT_AIRBORNE_GRACE_TICKS }
        .persistent(Codec.INT)
    }

    fun initialize() {}
}