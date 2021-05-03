package world.cepi.energy

import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension;
import net.minestom.server.utils.time.TimeUnit
import world.cepi.energy.command.EnergyCommand
import world.cepi.kstom.Manager
import world.cepi.kstom.addEventCallback
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister

class EnergyExtension : Extension() {

    override fun initialize() {
        MinecraftServer.getConnectionManager().addPlayerInitialization { player ->

            player.addEventCallback<PlayerSpawnEvent> {

                player.energy = player.maxEnergy

                Manager.scheduler.buildTask { player.energy += player.energyRegen }
                    .repeat(player.energyRegenTimeout.toLong(), TimeUnit.SECOND)
                    .schedule()

            }

            player.addEventCallback(::displayFood)
        }

        EnergyCommand.register()

        logger.info("[EnergyExtension] has been enabled!")
    }

    override fun terminate() {

        EnergyCommand.unregister()

        logger.info("[EnergyExtension] has been disabled!")
    }

}