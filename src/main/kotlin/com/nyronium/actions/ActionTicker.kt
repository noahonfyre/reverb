package com.nyronium.actions

import com.nyronium.registry.ReverbAttachments
import com.nyronium.util.Utils.isEffectivelyGrounded
import net.minecraft.world.entity.player.Player

object ActionTicker {
    const val DASH_GROUND_REPLENISHMENT = 8

    fun handle(player: Player) {
        var slamCooldownTicks = player.getAttachedOrCreate(ReverbAttachments.SLAM_COOLDOWN_TICKS)
        var dashCooldownTicks = player.getAttachedOrCreate(ReverbAttachments.DASH_COOLDOWN_TICKS)
        var stasisCooldownTicks = player.getAttachedOrCreate(ReverbAttachments.STASIS_COOLDOWN_TICKS)
        var stasisCharges = player.getAttachedOrCreate(ReverbAttachments.STASIS_CHARGES)
        var airborneGraceTicks = player.getAttachedOrCreate(ReverbAttachments.AIRBORNE_GRACE_TICKS)

        if (player.onGround()) {
            airborneGraceTicks = ReverbAttachments.DEFAULT_AIRBORNE_GRACE_TICKS
        } else {
            if(airborneGraceTicks > 0) airborneGraceTicks--
        }

        if (slamCooldownTicks > 0) slamCooldownTicks--
        if (dashCooldownTicks > 0) dashCooldownTicks--
        if (stasisCooldownTicks > 0) stasisCooldownTicks--

        if (stasisCooldownTicks == 0 && player.isEffectivelyGrounded()) {
            stasisCharges = (stasisCharges + 1).coerceAtMost(ReverbAttachments.DEFAULT_STASIS_CHARGES)
            if(stasisCharges < ReverbAttachments.DEFAULT_STASIS_CHARGES) {
                stasisCooldownTicks = ReverbAttachments.DEFAULT_STASIS_COOLDOWN_TICKS
            }
        }

        if(dashCooldownTicks > DASH_GROUND_REPLENISHMENT && player.isEffectivelyGrounded()) {
            dashCooldownTicks = DASH_GROUND_REPLENISHMENT
        }

        player.setAttached(ReverbAttachments.SLAM_COOLDOWN_TICKS, slamCooldownTicks)
        player.setAttached(ReverbAttachments.DASH_COOLDOWN_TICKS, dashCooldownTicks)
        player.setAttached(ReverbAttachments.STASIS_COOLDOWN_TICKS, stasisCooldownTicks)
        player.setAttached(ReverbAttachments.STASIS_CHARGES, stasisCharges)
        player.setAttached(ReverbAttachments.AIRBORNE_GRACE_TICKS, airborneGraceTicks)
    }
}