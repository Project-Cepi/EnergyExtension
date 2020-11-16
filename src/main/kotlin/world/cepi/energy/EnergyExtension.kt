package world.cepi.energy

import net.minestom.server.MinecraftServer
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.event.player.PlayerMoveEvent
import net.minestom.server.extensions.Extension;
import net.minestom.server.utils.time.TimeUnit

class EnergyExtension : Extension() {

    override fun initialize() {
        MinecraftServer.getConnectionManager().addPlayerInitialization { player ->
            player.energy = player.maxEnergy

            player.addEventCallback(PlayerBlockInteractEvent::class.java) { onAttack(it) }
            player.addEventCallback(EnergyChangeEvent::class.java) { displayFood(it) }

            MinecraftServer.getSchedulerManager().buildTask { player.energy += player.energyRegen }
                .repeat(player.energyRegenTimeout.toLong(), TimeUnit.SECOND)
                .schedule()
        }
        logger.info("[EnergyExtension] has been enabled!")
    }

    override fun terminate() {
        logger.info("[EnergyExtension] has been disabled!")
    }

}