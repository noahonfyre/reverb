package com.nyronium.util

import com.nyronium.registry.ReverbAttachments
import net.minecraft.world.entity.player.Player

object Utils {
    fun Player.isEffectivelyGrounded(): Boolean {
        return this.onGround() || this.getAttachedOrCreate(ReverbAttachments.AIRBORNE_GRACE_TICKS) > 0 || this.isInWater || this.onClimbable()
    }
}