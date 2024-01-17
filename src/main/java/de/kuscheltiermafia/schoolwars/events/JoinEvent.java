package de.kuscheltiermafia.schoolwars.events;

import de.kuscheltiermafia.schoolwars.SchoolWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent e){

        SchoolWars.setPlayerCount(SchoolWars.getPlayerCount() + 1);

        Player p = e.getPlayer();

        p.teleport(new Location(p.getWorld(), -24, 80, 175, 90, 0));

        for (Player pl : Bukkit.getOnlinePlayers()){

            pl.spawnParticle(Particle.LAVA, p.getLocation(), 40, 0, 0.2, 0);
            pl.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 10, 0, 0.2, 0);
            pl.spawnParticle(Particle.PORTAL, p.getLocation(), 100, 0, 0.2, 0);

        }


        p.sendMessage( ChatColor.YELLOW + "---------------SCHOOL WARS---------------");
        p.sendMessage("Willkommen in diesem grandiosen Spiel,");
        p.sendMessage("in dem du auch in deiner Freizeit");
        p.sendMessage("die " + ChatColor.RED + "Freuden der Schule" + ChatColor.WHITE + " erfahren kannst.");
        p.sendMessage( ChatColor.YELLOW + "-----------------------------------------");

    }

}
