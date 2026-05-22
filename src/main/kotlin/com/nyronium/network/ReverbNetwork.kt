package com.nyronium.network

import com.nyronium.actions.DashAction
import com.nyronium.actions.SlamAction
import com.nyronium.actions.StasisAction
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking


object ReverbNetwork {
    fun initialize() {
        PayloadTypeRegistry.serverboundPlay()
            .register(ServerboundSonicActionPayload.TYPE, ServerboundSonicActionPayload.CODEC)

        ServerPlayNetworking.registerGlobalReceiver(ServerboundSonicActionPayload.TYPE) {
            payload: ServerboundSonicActionPayload, context: ServerPlayNetworking.Context ->

            when (payload.kind) {
                "dash/forwards" -> {
                    DashAction.handle(context.player(), "forwards")
                }
                "dash/left" -> {
                    DashAction.handle(context.player(), "left")
                }
                "dash/backwards" -> {
                    DashAction.handle(context.player(), "backwards")
                }
                "dash/right" -> {
                    DashAction.handle(context.player(), "right")
                }
                "stasis" -> {
                    StasisAction.handle(context.player())
                }
                "slam" -> {
                    SlamAction.handle(context.player())
                }
            }
        }
    }
}