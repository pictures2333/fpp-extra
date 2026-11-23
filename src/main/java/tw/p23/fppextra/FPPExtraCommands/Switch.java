package tw.p23.fppextra.FPPExtraCommands;

import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;

import io.papermc.paper.command.brigadier.CommandSourceStack;

public class Switch {
    private final JavaPlugin plugin;

    public Switch(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public int execute(
        CommandContext<CommandSourceStack> ctx,
        String key,
        boolean value
    ) {
        this.plugin.getConfig().set(key, value);
        this.plugin.saveConfig();

        ctx.getSource().getSender().sendMessage(Component.text("[fpp-extra] ", NamedTextColor.AQUA)
            .append(Component.text(key + ": ", NamedTextColor.GRAY))
            .append(Component.text(value ? "ON" : "OFF",
                value ? NamedTextColor.GREEN : NamedTextColor.RED)));

        return Command.SINGLE_SUCCESS;
    }
}
