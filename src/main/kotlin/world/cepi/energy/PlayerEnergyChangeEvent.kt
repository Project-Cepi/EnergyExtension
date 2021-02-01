package world.cepi.energy

import net.minestom.server.entity.Player
import net.minestom.server.event.CancellableEvent
import net.minestom.server.event.PlayerEvent

class PlayerEnergyChangeEvent(
    val oldEnergy: Int,
    val newEnergy: Int,
    player: Player,
) : CancellableEvent, PlayerEvent(player) {

    private var cancelled = false

    override fun isCancelled() = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }
}