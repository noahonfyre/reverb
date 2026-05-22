package com.nyronium.content.item

import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.phys.HitResult

class EchoCharge(properties: Properties) : Item(properties) {
    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        if(level.isClientSide) return super.use(level, player, hand)
        level as ServerLevel

        val stack = player.getItemInHand(hand)

        val eyePosition = player.eyePosition
        val lookVec = player.lookAngle.normalize()

        val hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE)
        val pos = if(hitResult.type == HitResult.Type.BLOCK || hitResult.type == HitResult.Type.ENTITY) {
            val blockCenterVec = hitResult.blockPos.center
            val direction = blockCenterVec.subtract(eyePosition).normalize()
            blockCenterVec.subtract(direction.scale(1.25))
        } else {
            player.eyePosition.add(player.lookAngle.scale(3.0))
        }

        player.push(lookVec.reverse().scale(1.5))
        player.hurtMarked = true

        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.WARDEN_SONIC_BOOM, SoundSource.NEUTRAL, 0.75f, 1.75f)
        level.sendParticles(
            ParticleTypes.SONIC_BOOM,
            pos.x, pos.y, pos.z,
            1,
            0.15, 0.15, 0.15,
            1.0
        )
        player.cooldowns.addCooldown(stack, 12)

        stack.consume(1, player)
        return InteractionResult.SUCCESS
    }
}