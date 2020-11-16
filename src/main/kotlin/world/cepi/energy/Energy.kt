package world.cepi.energy

import net.minestom.server.data.DataImpl
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.event.player.PlayerMoveEvent
import kotlin.math.floor
import kotlin.math.round

const val keyEnergy = "energy"
const val keyEnergyMax = "energy-max"
const val keyEnergyRegen = "energy-max"
const val keyEnergyRegenTimeout = "energy-max"

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
        var cap = value
        if (value < 0) {
           cap = 0
        }
        initData(this)
        val event = EnergyChangeEvent(this.energy, cap, this)
        this.callCancellableEvent(EnergyChangeEvent::class.java, event) {
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
        return this.data!!.getOrDefault<Int>(keyEnergyRegen, 20)!!
    }
    set(value) {
        initData(this)
        return this.data!!.set(keyEnergyRegen, value)
    }

var Player.energyRegenTimeout: Int
    get() {
        initData(this)
        return this.data!!.getOrDefault<Int>(keyEnergyRegenTimeout, 20)!!
    }
    set(value) {
        initData(this)
        return this.data!!.set(keyEnergyRegenTimeout, value)
    }

fun displayFood(event: EnergyChangeEvent) {
    val units = floor(((event.newEnergy.toFloat() / event.player.maxEnergy.toFloat())*20).toFloat())
    println(units)
    event.player.food = units.toInt()
}

fun onAttack(event: PlayerBlockInteractEvent) {
    val player = event.player
    player.energy -= 1


}


