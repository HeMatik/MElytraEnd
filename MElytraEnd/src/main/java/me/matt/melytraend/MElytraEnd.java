package me.matt.melytraend;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class MElytraEnd extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        if (world.getEnvironment() == World.Environment.THE_END) {
            if (player.getGameMode() != GameMode.CREATIVE && player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.ELYTRA) {
                ItemStack elytra = player.getInventory().getChestplate();
                ItemMeta meta = elytra.getItemMeta();
                if (meta instanceof Damageable) {
                    Damageable damage = (Damageable) meta;
                    int previousDamage = damage.getDamage();
                    player.getInventory().setChestplate(new ItemStack(Material.AIR));
                    player.getInventory().addItem(elytra);
                    elytra = player.getInventory().getChestplate();
                    meta = elytra.getItemMeta();
                    damage = (Damageable) meta;
                    damage.setDamage(previousDamage);
                    elytra.setItemMeta(meta);
                    player.sendMessage("You cannot wear elytra in The End!");
                }
            }
        }
    }
}