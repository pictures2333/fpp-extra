package tw.p23.fppextra.FPPExtraCommands;

import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;

import io.papermc.paper.command.brigadier.CommandSourceStack;

public class Reload {
    private final JavaPlugin plugin;

    public Reload(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public int execute(CommandContext<CommandSourceStack> ctx) {
        this.plugin.reloadConfig();

        ctx.getSource().getSender().sendMessage(Component.text("[fpp-extra] ", NamedTextColor.AQUA)
            .append(Component.text("Configuration reloaded.", NamedTextColor.GREEN)));

        return Command.SINGLE_SUCCESS;
    }
}
