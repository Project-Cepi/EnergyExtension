package world.cepi.energy.command

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.energy.energy
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object EnergyCommand : Kommand({

    val set by literal
    val add by literal
    val remove by literal

    val amount = ArgumentType.Integer("amount").min(0).max(20)

    onlyPlayers

    syntax(set, amount) {
        player.energy = context.get(amount)
    }

    syntax(add, amount) {
        player.energy += context.get(amount)
    }

    syntax(remove, amount) {
        player.energy -= context.get(amount)
    }

}, "energy")