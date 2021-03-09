package world.cepi.energy.command

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.energy.energy
import world.cepi.kstom.command.arguments.asSubcommand
import world.cepi.kstom.command.addSyntax

class EnergyCommand : Command("energy") {

    init {

        val set = "set".asSubcommand()
        val amount = ArgumentType.Integer("amount").min(0).max(20)

        addSyntax(set, amount) { sender, args ->

            val player = sender as Player

            player.energy = args.get(amount)
        }

    }

}