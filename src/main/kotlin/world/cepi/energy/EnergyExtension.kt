package world.cepi.energy

import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension
import net.minestom.server.utils.time.TimeUnit
import world.cepi.energy.command.EnergyCommand
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node

class EnergyExtension : Extension() {

    override fun initialize(): LoadStatus {

        val playerNode = EventNode.type("energy-player-set", EventFilter.PLAYER)

        playerNode.listenOnly<PlayerSpawnEvent> {

            player.energy = player.maxEnergy

            Manager.scheduler.buildTask { player.energy += player.energyRegen }
                .repeat(player.energyRegenInterval.toLong(), TimeUnit.SECOND)
                .schedule()

        }


        playerNode.listenOnly(::displayFood)

        node.addChild(playerNode)

        EnergyCommand.register()

        log.info("[EnergyExtension] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {

        EnergyCommand.unregister()

        log.info("[EnergyExtension] has been disabled!")
    }

}