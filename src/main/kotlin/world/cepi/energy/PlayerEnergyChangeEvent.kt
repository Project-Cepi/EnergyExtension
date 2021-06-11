package world.cepi.energy

import net.minestom.server.entity.Player
import net.minestom.server.event.trait.CancellableEvent
import net.minestom.server.event.trait.PlayerEvent

class PlayerEnergyChangeEvent(
    val oldEnergy: Int,
    val newEnergy: Int,
    private val _player: Player,
) : CancellableEvent, PlayerEvent {

    override fun getPlayer() = _player

    private var cancelled = false

    override fun isCancelled() = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }
}