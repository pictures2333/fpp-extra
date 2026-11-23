package tw.p23.fppextra.FPPExtraCommands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;

import io.papermc.paper.command.brigadier.CommandSourceStack;

import tw.p23.fppextra.Utility.*;

public class ListBots {
    private FilterFakePlayer filterFakePlayer;

    public ListBots() {
        this.filterFakePlayer = new FilterFakePlayer();
    }

    public int execute(CommandContext<CommandSourceStack> ctx) {
        int counter = 0;
        var sender = ctx.getSource().getSender();

        sender.sendMessage(Component.text("[fpp-extra] ", NamedTextColor.AQUA)
            .append(Component.text("Bots", NamedTextColor.WHITE, TextDecoration.BOLD)));

        for (World world : Bukkit.getWorlds()) {
            for (Player player : world.getEntitiesByClass(Player.class)) {
                if (this.filterFakePlayer.isFakePlayer(player)) {
                    counter += 1;

                    sender.sendMessage(Component.text("  - ", NamedTextColor.DARK_GRAY)
                        .append(Component.text(player.getName(), NamedTextColor.AQUA))
                        .append(Component.text(" | ", NamedTextColor.DARK_GRAY))
                        .append(Component.text(player.getUniqueId().toString(), NamedTextColor.GRAY)));
                }
            }
        }

        if (counter == 0) {
            sender.sendMessage(Component.text("  No bots online.", NamedTextColor.GRAY));
        }

        sender.sendMessage(Component.text("Total: ", NamedTextColor.GRAY)
            .append(Component.text(counter, NamedTextColor.WHITE)));

        return Command.SINGLE_SUCCESS;
    }
}
