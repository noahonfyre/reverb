package com.nyronium

import com.nyronium.network.ReverbNetwork
import com.nyronium.registry.ReverbAttachments
import com.nyronium.registry.ReverbDataComponents
import com.nyronium.registry.ReverbItems
import com.nyronium.registry.ReverbTabs
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Reverb : ModInitializer {
    const val ID = "reverb"
    val LOGGER: Logger = LoggerFactory.getLogger(ID)

	override fun onInitialize() {
		LOGGER.info("reverb")

        ReverbItems.initialize()
        ReverbTabs.initialize()
        ReverbNetwork.initialize()
        ReverbDataComponents.initialize()
        ReverbAttachments.initialize()
	}
}