package tw.p23.fppextra.Utility;

import java.util.regex.Pattern;

import org.bukkit.entity.Player;

public class FilterFakePlayer {
    private static final Pattern FPP_UUID = Pattern.compile(
        "^fb07[0-9a-f]{4}-[0-9a-f]{4}-0[0-9a-f]{3}-[0-9a-f]{4}-[0-9a-f]{12}$"
    );

    public boolean isFakePlayer(Player player) {
        return FPP_UUID.matcher(player.getUniqueId().toString()).matches();
    }
}
