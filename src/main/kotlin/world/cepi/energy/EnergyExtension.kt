package world.cepi.energy

import net.minestom.server.extensions.Extension;

class EnergyExtension : Extension() {

    override fun initialize() {
        logger.info("[EnergyExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[EnergyExtension] has been disabled!")
    }

}