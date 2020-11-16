package world.cepi.energy

import net.minestom.server.entity.Player
import net.minestom.server.event.CancellableEvent

class EnergyChangeEvent(
    val oldEnergy: Int,
    val newEnergy: Int,
    val player: Player,
) : CancellableEvent() {
}