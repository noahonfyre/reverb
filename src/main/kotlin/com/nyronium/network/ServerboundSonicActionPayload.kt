package com.nyronium.network

import com.nyronium.Reverb
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier

data class ServerboundSonicActionPayload(val kind: String) : CustomPacketPayload {
    companion object {
        val PAYLOAD_ID: Identifier = Identifier.fromNamespaceAndPath(Reverb.ID, "sonic_action")
        val TYPE: CustomPacketPayload.Type<ServerboundSonicActionPayload> = CustomPacketPayload.Type(PAYLOAD_ID)
        val CODEC: StreamCodec<RegistryFriendlyByteBuf, ServerboundSonicActionPayload> = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            ServerboundSonicActionPayload::kind,
            ::ServerboundSonicActionPayload
        )
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return TYPE
    }
}
