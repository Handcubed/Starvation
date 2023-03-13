package xyz.amayakasa.starvation;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Don't forget about the food.
 * This plugin is designed to complicate the game by introducing the importance of controlling your food level.
 *
 * @author Yuly Gorbatkov, Ivan Volokitin
 */
@SuppressWarnings("unused")
public final class Starvation extends JavaPlugin {
    @Override
    public void onEnable() {
        StarvationRunnable starvation = new StarvationRunnable();

        // It will work every 2 seconds. Health is restored every 4 seconds (by default).
        starvation.runTaskTimer(this, 0L, 40L);
    }
}