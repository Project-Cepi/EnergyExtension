package world.cepi.energy.command

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.energy.energy
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal

object EnergyCommand : Command("energy") {

    init {

        val set = "set".literal()
        val add = "add".literal()
        val remove = "remove".literal()

        val amount = ArgumentType.Integer("amount").min(0).max(20)

        addSyntax(set, amount) {

            val player = sender as Player

            player.energy = context.get(amount)
        }

        addSyntax(add, amount) {
            val player = sender as Player

            player.energy += context.get(amount)
        }

        addSyntax(remove, amount) {
            val player = sender as Player
            player.energy -= context.get(amount)
        }

    }

}