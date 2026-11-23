package tw.p23.fppextra.FPPExtraCommands;

import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;

import io.papermc.paper.command.brigadier.CommandSourceStack;

public class Status {
    private final JavaPlugin plugin;

    public Status(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public int execute(CommandContext<CommandSourceStack> ctx) {
        boolean ignoreSleep = this.plugin.getConfig().getBoolean("ignore-sleep", false);
        boolean invulnerable = this.plugin.getConfig().getBoolean("invulnerable", false);

        var sender = ctx.getSource().getSender();
        sender.sendMessage(Component.text("[fpp-extra] ", NamedTextColor.AQUA)
            .append(Component.text("Status", NamedTextColor.WHITE, TextDecoration.BOLD)));
        sender.sendMessage(Component.text("  ignore-sleep: ", NamedTextColor.GRAY)
            .append(Component.text(ignoreSleep ? "ON" : "OFF",
                ignoreSleep ? NamedTextColor.GREEN : NamedTextColor.RED)));
        sender.sendMessage(Component.text("  invulnerable: ", NamedTextColor.GRAY)
            .append(Component.text(invulnerable ? "ON" : "OFF",
                invulnerable ? NamedTextColor.GREEN : NamedTextColor.RED)));

        return Command.SINGLE_SUCCESS;
    }
}
