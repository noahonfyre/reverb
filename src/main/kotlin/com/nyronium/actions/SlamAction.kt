package com.nyronium.actions

import com.nyronium.registry.ReverbAttachments
import com.nyronium.util.Utils.isEffectivelyGrounded
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3

object SlamAction {
    fun handle(player: Player) {
        val slamCooldownTicks = player.getAttachedOrCreate(ReverbAttachments.SLAM_COOLDOWN_TICKS)
        if(slamCooldownTicks != 0) return

        if(player.isEffectivelyGrounded()) return

        player.push(Vec3(0.0, -1.25, 0.0))
        player.hurtMarked = true

        player.setIgnoreFallDamageFromCurrentImpulse(true, player.deltaMovement)

        player.setAttached(ReverbAttachments.SLAM_COOLDOWN_TICKS, ReverbAttachments.DEFAULT_SLAM_COOLDOWN_TICKS)
    }
}