package world.cepi.energy

import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension;
import net.minestom.server.utils.time.TimeUnit
import world.cepi.energy.command.EnergyCommand
import world.cepi.kstom.addEventCallback

class EnergyExtension : Extension() {

    override fun initialize() {
        MinecraftServer.getConnectionManager().addPlayerInitialization { player ->

            player.addEventCallback<PlayerSpawnEvent> {

                player.energy = player.maxEnergy

                MinecraftServer.getSchedulerManager().buildTask { player.energy += player.energyRegen }
                    .repeat(player.energyRegenTimeout.toLong(), TimeUnit.SECOND)
                    .schedule()

            }

            player.addEventCallback(PlayerEnergyChangeEvent::class.java) { displayFood(it) }
        }

        MinecraftServer.getCommandManager().register(EnergyCommand())

        logger.info("[EnergyExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[EnergyExtension] has been disabled!")
    }

}