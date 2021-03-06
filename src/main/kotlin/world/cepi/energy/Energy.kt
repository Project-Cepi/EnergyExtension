package world.cepi.energy

import net.minestom.server.data.DataImpl
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerBlockInteractEvent
import world.cepi.kstom.Manager
import kotlin.math.floor

const val keyEnergy = "energy"
const val keyEnergyMax = "energy-max"
const val keyEnergyRegen = "energy-regen"
const val keyEnergyRegenTimeout = "energy-regen-timeout"

private fun initData(player: Player) {
    if (player.data == null)
        player.data = DataImpl()
}

var Player.energy: Int
    get() {
        initData(this)
        return this.data!!.getOrDefault<Int>(keyEnergy, 20)!!
    }
    set(value) {
        val cap = value.coerceIn(0..this.maxEnergy)
        initData(this)
        val event = PlayerEnergyChangeEvent(this.energy, cap, this)
        Manager.globalEvent.callCancellable(event) {
            if (!event.isCancelled)
                this.data!!.set(keyEnergy, cap)
        }
    }

var Player.maxEnergy: Int
    get() {
        initData(this)
        return this.data!!.getOrDefault<Int>(keyEnergyMax, 20)!!
    }
    set(value) {
        initData(this)
        return this.data!!.set(keyEnergyMax, value)
    }

var Player.energyRegen: Int
    get() {
        initData(this)
        return this.data!!.getOrDefault<Int>(keyEnergyRegen, 4)!!
    }
    set(value) {
        initData(this)
        return this.data!!.set(keyEnergyRegen, value)
    }

var Player.energyRegenInterval: Int
    get() {
        initData(this)
        return this.data!!.getOrDefault<Int>(keyEnergyRegenTimeout, 1)!!
    }
    set(value) {
        initData(this)
        return this.data!!.set(keyEnergyRegenTimeout, value)
    }

fun displayFood(event: PlayerEnergyChangeEvent) = with(event) {
    val units = floor(((newEnergy.toFloat() / player.maxEnergy.toFloat()) * 20))
    player.food = units.toInt()
}

