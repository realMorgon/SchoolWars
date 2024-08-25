/**
 * ███╗   ███╗ █████╗ ██████╗ ███████╗    ██████╗ ██╗   ██╗
 * ████╗ ████║██╔══██╗██╔══██╗██╔════╝    ██╔══██╗╚██╗ ██╔╝
 * ██╔████╔██║███████║██║  ██║█████╗      ██████╔╝ ╚████╔╝
 * ██║╚██╔╝██║██╔══██║██║  ██║██╔══╝      ██╔══██╗  ╚██╔╝
 * ██║ ╚═╝ ██║██║  ██║██████╔╝███████╗    ██████╔╝   ██║
 * ╚═╝     ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝    ╚═════╝    ╚═╝
 *
 * ██╗  ██╗██╗   ██╗███████╗ ██████╗██╗  ██╗███████╗██╗  ████████╗██╗███████╗██████╗ ███╗   ███╗ █████╗ ███████╗██╗ █████╗
 * ██║ ██╔╝██║   ██║██╔════╝██╔════╝██║  ██║██╔════╝██║  ╚══██╔══╝██║██╔════╝██╔══██╗████╗ ████║██╔══██╗██╔════╝██║██╔══██╗
 * █████╔╝ ██║   ██║███████╗██║     ███████║█████╗  ██║     ██║   ██║█████╗  ██████╔╝██╔████╔██║███████║█████╗  ██║███████║
 * ██╔═██╗ ██║   ██║╚════██║██║     ██╔══██║██╔══╝  ██║     ██║   ██║██╔══╝  ██╔══██╗██║╚██╔╝██║██╔══██║██╔══╝  ██║██╔══██║
 * ██║  ██╗╚██████╔╝███████║╚██████╗██║  ██║███████╗███████╗██║   ██║███████╗██║  ██║██║ ╚═╝ ██║██║  ██║██║     ██║██║  ██║
 * ╚═╝  ╚═╝ ╚═════╝ ╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝   ╚═╝╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝
 *
 * This is a plugin from Morgon and CrAzyA22 - Unless explicitly stated otherwise you are not permitted to use any of the given code!
 *
 */

package de.kuscheltiermafia.schoolwars.events;

import de.kuscheltiermafia.schoolwars.SchoolWars;
import de.kuscheltiermafia.schoolwars.gameprep.Teams;
import de.kuscheltiermafia.schoolwars.items.Items;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPLayerJoin(PlayerJoinEvent e){

        SchoolWars.setPlayerCount(SchoolWars.getPlayerCount() + 1);

        Player p = e.getPlayer();

        p.teleport(new Location(p.getWorld(), -24, 80.5, 176, 90, 0));
        p.setRespawnLocation(new Location(p.getWorld(), -33.5, 88, 145.5, -90, 0));

//Spawn particles
        for (Player pl : Bukkit.getOnlinePlayers()){

            pl.spawnParticle(Particle.LAVA, p.getLocation(), 40, 0, 0.2, 0);
            pl.spawnParticle(Particle.EXPLOSION, p.getLocation(), 10, 0, 0.2, 0);
            pl.spawnParticle(Particle.PORTAL, p.getLocation(), 100, 0, 0.2, 0);

        }

//Send Welcome Message
        p.sendMessage( ChatColor.YELLOW + "---------------SCHOOL WARS---------------");
        p.sendMessage("Willkommen in diesem grandiosen Spiel,");
        p.sendMessage("in dem du auch in deiner Freizeit");
        p.sendMessage("die " + ChatColor.RED + "Freuden der Schule" + ChatColor.WHITE + " erfahren kannst.");
        p.sendMessage( ChatColor.YELLOW + "-----------------------------------------");

//Put Player back in Team after Disconnect
        if (SchoolWars.gameStarted){

            p.sendMessage("Das Spiel hat bereits begonnen.");

            if (Teams.naturwissenschaftler.contains(p.getName()) ){
                Teams.configurePlayer(p.getName(), "naturwissenschaftler");
                p.setGameMode(GameMode.SURVIVAL);
            } else if (Teams.sportler.contains(p.getName())){
                Teams.configurePlayer(p.getName(), "sportler");
                p.setGameMode(GameMode.SURVIVAL);
            } else if (Teams.sprachler.contains(p.getName())){
                Teams.configurePlayer(p.getName(), "sprachler");
                p.setGameMode(GameMode.SURVIVAL);
            } else {
                p.sendMessage(ChatColor.YELLOW + "[SchoolWars] Du bist keinem Team zugeordnet.");
                p.setGameMode(GameMode.SPECTATOR);
            }

        }else {
            e.getPlayer().sendMessage(ChatColor.YELLOW + "[SchoolWars] Das Spiel hat noch nicht begonnen.");
            p.setGameMode(GameMode.SURVIVAL);
        }

        e.setJoinMessage("");

    }

}
