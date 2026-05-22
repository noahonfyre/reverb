package com.nyronium.actions

import com.nyronium.registry.ReverbAttachments
import com.nyronium.util.Utils.isEffectivelyGrounded
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.player.Player

object StasisAction {
    fun handle(player: Player) {
        if(player.isEffectivelyGrounded() || player.isInWater) return
        if(player.abilities.flying) return
        var stasisCharges = player.getAttachedOrCreate(ReverbAttachments.STASIS_CHARGES)
        if(stasisCharges == 0) return

        player.push(
            player.lookAngle
                .scale(0.5)
                .multiply(1.0, 0.0, 1.0)
                .add(0.0, -player.deltaMovement.y + 1.0, 0.0)
        )
        player.hurtMarked = true

        stasisCharges--

        player.setAttached(ReverbAttachments.STASIS_CHARGES, stasisCharges)

        if(player.level().isClientSide) return
        val level = player.level() as ServerLevel

        val pos = player.position()

        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.WARDEN_STEP, SoundSource.NEUTRAL, 1.0f, 1.0f)
        level.sendParticles(
            ParticleTypes.SONIC_BOOM,
            pos.x, pos.y, pos.z,
            1,
            0.15, 0.15, 0.15,
            1.0
        )
    }
}