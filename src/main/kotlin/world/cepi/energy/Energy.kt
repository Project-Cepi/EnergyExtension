package world.cepi.energy

import net.minestom.server.entity.Player
import net.minestom.server.tag.Tag
import world.cepi.kstom.Manager
import kotlin.math.floor

val keyEnergy = Tag.Integer("energy")
val keyEnergyMax = Tag.Integer("energy-max")
val keyEnergyRegen = Tag.Integer("energy-regen")
val keyEnergyRegenTimeout = Tag.Integer("energy-regen-timeout")

var Player.energy: Int
    get() {
        return this.getTag(keyEnergy) ?: 20
    }
    set(value) {
        val cap = value.coerceIn(0..this.maxEnergy)
        val event = PlayerEnergyChangeEvent(this.energy, cap, this)
        Manager.globalEvent.callCancellable(event) {
            if (!event.isCancelled)
                this.setTag(keyEnergy, cap)
        }
    }

var Player.maxEnergy: Int
    get() {
        return this.getTag(keyEnergyMax) ?: 20
    }
    set(value) {
        return this.setTag(keyEnergyMax, value)
    }

var Player.energyRegen: Int
    get() {
        return this.getTag(keyEnergyRegen) ?: 4
    }
    set(value) {
        return this.setTag(keyEnergyRegen, value)
    }

var Player.energyRegenInterval: Int
    get() {
        return this.getTag(keyEnergyRegenTimeout) ?: 1
    }
    set(value) {
        return this.setTag(keyEnergyRegenTimeout, value)
    }

fun displayFood(event: PlayerEnergyChangeEvent) = with(event) {
    val units = floor(((newEnergy.toFloat() / player.maxEnergy.toFloat()) * 20))
    player.food = units.toInt()
}

