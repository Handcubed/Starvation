package xyz.amayakasa.starvation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Set;

/**
 * When a player reaches a certain food level, negative effects are applying on him.
 *
 * @author Yuly Gorbatkov, Ivan Volokitin.
 * Special thanks to Aram Elbadian.
 */
public class StarvationRunnable extends BukkitRunnable {
    /**
     * The food level and its negative effects.
     * 5.5 chicken legs — Weakness I & Mining Fatigue II.
     * 3 chicken legs — Slowness I.
     * 1 chicken legs — Blindness II.
     */
    private final Map<Integer, Set<PotionEffect>> negativeEffects = ImmutableMap.of(
            11, ImmutableSet.of(
                    new PotionEffect(
                            PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0,
                            false, false, false
                    ),
                    new PotionEffect(
                            PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 1,
                            false, false, false
                    )
            ),
            6, ImmutableSet.of(
                    new PotionEffect(
                            PotionEffectType.SLOW, Integer.MAX_VALUE, 0,
                            false, false, false
                    )
            ),
            2, ImmutableSet.of(
                    new PotionEffect(
                            PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1,
                            false, false, false
                    )
            )
    );

    /**
     * Run starvation logic.
     */
    @Override
    public void run() {
        negativeEffects.forEach((foodLevel, effects) -> {
            var onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            onlinePlayers.forEach(player -> {
                if (player.getFoodLevel() <= foodLevel) player.addPotionEffects(effects);

                else effects.stream().map(PotionEffect::getType).forEach(player::removePotionEffect);
            });
        });
    }
}
