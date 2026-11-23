package tw.p23.fppextra;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

import org.bukkit.plugin.java.JavaPlugin;

import tw.p23.fppextra.FPPExtraCommands.*;
import tw.p23.fppextra.Tasks.*;

public final class FPPExtra extends JavaPlugin {
    Task task;

    @Override
    public void onEnable() {
        // default config
        saveDefaultConfig();

        // command tree
        Reload commandReload = new Reload(this);
        ListBots commandListBot = new ListBots();
        Status commandStatus = new Status(this);
        Switch commandSwitch = new Switch(this);

        getLifecycleManager().registerEventHandler(
            LifecycleEvents.COMMANDS,
            event -> {
                event.registrar().register(
                    Commands.literal("fpp-extra")

                    .then(
                        Commands.literal("reload")
                        .requires(
                            src -> src.getSender().hasPermission("fpp-extra.reload")
                        )
                        .executes(
                            ctx -> commandReload.execute(ctx)
                        )
                    )

                    .then(
                        Commands.literal("listbots")
                        .requires(
                            src -> src.getSender().hasPermission("fpp-extra.listbots")
                        )
                        .executes(
                            ctx -> commandListBot.execute(ctx)
                        )
                    )

                    .then(
                        Commands.literal("status")
                        .requires(
                            src -> src.getSender().hasPermission("fpp-extra.status")
                        )
                        .executes(
                            ctx -> commandStatus.execute(ctx)
                        )
                    )

                    .then(
                        Commands.literal("ignore-sleep")
                        .requires(
                            src -> src.getSender().hasPermission("fpp-extra.switch")
                        )
                        .then(
                            Commands.literal("on")
                            .requires(
                                src -> src.getSender().hasPermission("fpp-extra.switch")
                            )
                            .executes(
                                ctx -> commandSwitch.execute(ctx, "ignore-sleep", true)
                            )
                        )
                        .then(
                            Commands.literal("off")
                            .requires(
                                src -> src.getSender().hasPermission("fpp-extra.switch")
                            )
                            .executes(
                                ctx -> commandSwitch.execute(ctx, "ignore-sleep", false)
                            )
                        )
                    )

                    .then(
                        Commands.literal("invulnerable")
                        .requires(
                            src -> src.getSender().hasPermission("fpp-extra.switch")
                        )
                        .then(
                            Commands.literal("on")
                            .requires(
                                src -> src.getSender().hasPermission("fpp-extra.switch")
                            )
                            .executes(
                                ctx -> commandSwitch.execute(ctx, "invulnerable", true)
                            )
                        )
                        .then(
                            Commands.literal("off")
                            .requires(
                                src -> src.getSender().hasPermission("fpp-extra.switch")
                            )
                            .executes(
                                ctx -> commandSwitch.execute(ctx, "invulnerable", false)
                            )
                        )
                    )
                    
                    .build(),
                    "fpp-extra commands"
                );
            }
        );

        // scheduler
        this.task = new Task(this);
        task.start();

        // log
        getLogger().info("fpp-extra enabled!");
    }

    @Override
    public void onDisable() {
        if (this.task != null) {
            this.task.stop();
        }
    }
}