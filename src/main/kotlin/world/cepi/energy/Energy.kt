package world.cepi.energy

import net.minestom.server.data.DataImpl
import net.minestom.server.entity.Player

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
        initData(this)
        return this.data!!.set(keyEnergy, value)
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