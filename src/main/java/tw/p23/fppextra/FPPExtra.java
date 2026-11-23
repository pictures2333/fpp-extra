package tw.p23.fppextra;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

import org.bukkit.plugin.java.JavaPlugin;

import tw.p23.fppextra.FPPExtraCommands.*;

public final class FPPExtra extends JavaPlugin {

    @Override
    public void onEnable() {
        // default config
        saveDefaultConfig();

        // command tree
        ListBots commandListBot = new ListBots(this);
        Status commandStatus = new Status(this);
        Switch commandSwitch = new Switch(this);

        getLifecycleManager().registerEventHandler(
            LifecycleEvents.COMMANDS,
            event -> {
                event.registrar().register(
                    Commands.literal("fpp-extra")

                    // reload
                    // scheduler

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

        // log
        getLogger().info("fpp-extra enabled!");
    }
}