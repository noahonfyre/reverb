package com.nyronium.content.item

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.world.level.Level

class MagnetItem(properties: Properties) : Item(properties.stacksTo(1)) {
    override fun getUseAnimation(itemStack: ItemStack): ItemUseAnimation {
        return ItemUseAnimation.BLOCK
    }

    override fun getUseDuration(itemStack: ItemStack, user: LivingEntity): Int {
        return 30*20
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        player.startUsingItem(hand)
        return super.use(level, player, hand)
    }

    override fun onUseTick(level: Level, livingEntity: LivingEntity, itemStack: ItemStack, ticksRemaining: Int) {
        val itemEntities = level.getEntitiesOfClass(ItemEntity::class.java, livingEntity.boundingBox.inflate(7.5))

        val livingPos = livingEntity.position()
        itemEntities.forEach { itemEntity ->
            val itemPos = itemEntity.position()
            val distanceSqr = livingPos.distanceToSqr(itemPos)
            if(distanceSqr <= 1.5) return@forEach
            if(!itemEntity.hasGlowingTag()) itemEntity.setGlowingTag(true)
            itemEntity.push(livingPos.subtract(itemPos).normalize().scale(1/distanceSqr))
        }
    }
}