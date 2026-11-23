package tw.p23.fppextra.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import tw.p23.fppextra.Utility.*;

public class Task {
    private FilterFakePlayer filterFakePlayer;
    private final JavaPlugin plugin;
    private BukkitTask task;

    public Task(JavaPlugin plugin) {
        this.filterFakePlayer = new FilterFakePlayer();
        this.plugin = plugin;
    }

    public void start() {
        task = Bukkit.getScheduler().runTaskTimer(
            plugin,
            () -> updatePlayers(),
            0L,
            20L
        );
    }

    private void updatePlayers() {
        boolean ignoreSleep = plugin.getConfig().getBoolean("ignore-sleep", false);
        boolean invincible = plugin.getConfig().getBoolean("invulnerable", false);

        for (World world : Bukkit.getWorlds()) {
            for (Player player : world.getEntitiesByClass(Player.class)) {
                if (this.filterFakePlayer.isFakePlayer(player)) {
                    player.setSleepingIgnored(ignoreSleep);
                    
                    if (invincible) {
                        protectPlayer(player);
                    }
                }
            }
        }
    }

    private void protectPlayer(Player player) {
        player.addPotionEffect(new PotionEffect(
            PotionEffectType.RESISTANCE,
            20 * 2,
            100,
            true,
            false
        ));

        player.addPotionEffect(new PotionEffect(
            PotionEffectType.REGENERATION,
            20 * 2,
            100,
            true,
            false
        ));

        AttributeInstance health = player.getAttribute(
            Attribute.MAX_HEALTH
        );

        if (health != null && !player.isDead()) {
            player.setHealth(health.getValue());
        }
    }

    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}