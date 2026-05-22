package com.nyronium.client

import com.nyronium.network.ServerboundSonicActionPayload
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.Minecraft


object ReverbClient : ClientModInitializer {
    var dashWasDown = false
    var stasisWasDown = false
    var slamWasDown = false

	override fun onInitializeClient() {
        ReverbKeyMappings.initialize()

        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: Minecraft ->
            val options = Minecraft.getInstance().options

            if (ReverbKeyMappings.SONIC_DASH.isDown && !dashWasDown) {
                val type = when {
                    options.keyLeft.isDown -> "left"
                    options.keyDown.isDown -> "backwards"
                    options.keyRight.isDown -> "right"
                    else -> "forwards"
                }
                ClientPlayNetworking.send(ServerboundSonicActionPayload("dash/$type"))
                dashWasDown = true
            } else if(!ReverbKeyMappings.SONIC_DASH.isDown && dashWasDown) {
                dashWasDown = false
            }

            if (ReverbKeyMappings.SONIC_STASIS.isDown && !stasisWasDown) {
                ClientPlayNetworking.send(ServerboundSonicActionPayload("stasis"))
                stasisWasDown = true
            } else if(!ReverbKeyMappings.SONIC_STASIS.isDown && stasisWasDown) {
                stasisWasDown = false
            }

            if (ReverbKeyMappings.SONIC_SLAM.isDown && !slamWasDown) {
                ClientPlayNetworking.send(ServerboundSonicActionPayload("slam"))
                slamWasDown = true
            } else if(!ReverbKeyMappings.SONIC_SLAM.isDown && slamWasDown) {
                slamWasDown = false
            }
        })
	}
}