package world.cepi.energy

import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension;
import net.minestom.server.utils.time.TimeUnit
import world.cepi.energy.command.EnergyCommand
import world.cepi.kstom.Manager
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.kstom.event.listenOnly

class EnergyExtension : Extension() {

    override fun initialize() {

        val playerNode = EventNode.type("energy-player-set", EventFilter.PLAYER)

        playerNode.listenOnly<PlayerSpawnEvent> {

            player.energy = player.maxEnergy

            Manager.scheduler.buildTask { player.energy += player.energyRegen }
                .repeat(player.energyRegenTimeout.toLong(), TimeUnit.SECOND)
                .schedule()

        }


        playerNode.listenOnly(::displayFood)

        eventNode.addChild(playerNode)

        EnergyCommand.register()

        logger.info("[EnergyExtension] has been enabled!")
    }

    override fun terminate() {

        EnergyCommand.unregister()

        logger.info("[EnergyExtension] has been disabled!")
    }

}