package tw.p23.fppextra;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class FPPExtra extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("FPPExtra enabled!");

        getCommand("plugin").setExecutor(this);
    }

    @Override
    public boolean onCommand(
        CommandSender sender,
        Command command,
        String label,
        String[] args
    ) {
        getLogger().info(String.format("args.length: %d", args.length));
        sender.sendMessage("Hello, World!");
        return true;
    }
}