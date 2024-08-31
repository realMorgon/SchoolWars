package de.kuscheltiermafia.schoolwars.events;

import de.kuscheltiermafia.schoolwars.SchoolWars;
import de.kuscheltiermafia.schoolwars.items.Items;
import de.kuscheltiermafia.schoolwars.mechanics.ParticleHandler;
import de.kuscheltiermafia.schoolwars.mechanics.ProgressBarHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Minasisierung implements Listener {

    public static int minasDauer = 20 * 60 * 5;

    public static void onMinasisierung(Player p) {
        for(ItemStack item : p.getInventory().getContents()) {
            if(item.equals(Items.minas_flasche)) {
                item.setType(Items.buffed_minas_flasche.getType());
                item.setItemMeta(Items.buffed_minas_flasche.getItemMeta());
            }
            if(item.equals(Items.attack_stuhl)) {
                item.setType(Items.buffed_stuhl.getType());
                item.setItemMeta(Items.buffed_stuhl.getItemMeta());
            }
        }


        for(int i = 0; i < minasDauer; i++) {
            int finalI = i;
            new BukkitRunnable() {
                @Override
                public void run() {
                    double calcProgress = (double) finalI / minasDauer * 100;
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY + "-- Autismus: " + ProgressBarHandler.progressBarsUpdate(calcProgress, ChatColor.DARK_RED) + ChatColor.GRAY + " --"));

                    if(finalI == minasDauer - 1) {
                        for(ItemStack item : p.getInventory().getContents()) {
                            if(item.equals(Items.buffed_minas_flasche)) {
                                item.setType(Items.minas_flasche.getType());
                                item.setItemMeta(Items.minas_flasche.getItemMeta());
                            }
                            if(item.equals(Items.buffed_stuhl)) {
                                item.setType(Items.attack_stuhl.getType());
                                item.setItemMeta(Items.attack_stuhl.getItemMeta());
                            }
                        }
                    }
                }
            }.runTaskLater(SchoolWars.getPlugin(), i);
        }
    }


    @EventHandler
    public void OnStuhlHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player hitter = (Player) e.getDamager();
            Player hitted = (Player) e.getEntity();

            if(hitter.getInventory().getItemInMainHand().equals(Items.buffed_stuhl) && !hitted.getLocation().subtract(0, 2, 0).getBlock().getType().equals(Material.AIR)) {
                hitted.teleport(hitted.getLocation().subtract(0, 1, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 999999, 255, false, false, false));

                ParticleHandler.createParticles(hitted.getLocation(), Particle.CRIT, 20, 0.2, true, null);
                ParticleHandler.createParticles(hitted.getLocation(), Particle.CLOUD, 30, 0.4, true, null);
            }

        }
    }
}
