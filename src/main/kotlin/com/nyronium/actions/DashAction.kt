package com.nyronium.actions

import com.nyronium.registry.ReverbAttachments
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.player.Player

object DashAction {
    fun handle(player: Player, type: String) {
        val dashCooldownTicks = player.getAttachedOrCreate(ReverbAttachments.DASH_COOLDOWN_TICKS)

        if(dashCooldownTicks != 0) return
        if(player.onGround()) return

        val baseImpulse = when(type) {
            "left" -> player.lookAngle.reverse().rotateClockwise90()
            "right" -> player.lookAngle.rotateClockwise90()
            "backwards" -> player.lookAngle.reverse()
            else -> player.lookAngle
        }

        player.push(
            baseImpulse
                .scale(0.5)
                .horizontal()
                .normalize()
                .add(0.0, 0.05, 0.0)
        )
        player.hurtMarked = true

        player.setAttached(ReverbAttachments.DASH_COOLDOWN_TICKS, ReverbAttachments.DEFAULT_DASH_COOLDOWN_TICKS)

        if(player.level().isClientSide) return
        val level = player.level() as ServerLevel

        val pos = player.eyePosition.subtract(0.0, 0.5, 0.0).add(baseImpulse.reverse().horizontal().normalize().scale(1.1))

        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.WARDEN_STEP, SoundSource.NEUTRAL, 1.0f, 0.25f)
        level.sendParticles(
            ParticleTypes.SONIC_BOOM,
            pos.x, pos.y, pos.z,
            1,
            0.15, 0.15, 0.15,
            1.0
        )
    }
}