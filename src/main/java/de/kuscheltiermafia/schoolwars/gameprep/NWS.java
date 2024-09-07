package de.kuscheltiermafia.schoolwars.gameprep;

import de.kuscheltiermafia.schoolwars.SchoolWars;
import de.kuscheltiermafia.schoolwars.items.Items;
import de.kuscheltiermafia.schoolwars.mechanics.Ranzen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

import static de.kuscheltiermafia.schoolwars.mechanics.Ranzen.ranzenAmount;

public class NWS {

    public ArrayList<String> mitglieder = new ArrayList<String>();
    public String teamName = ChatColor.GREEN + "Naturwissenschaftler";

    public void prepare(){
        for(String s : mitglieder){
            Player p = Bukkit.getPlayer(s);
            readyPlayer(p);
        }
    }

    public void readyPlayer(Player p){
        p.sendMessage(ChatColor.YELLOW + "[SchoolWars] Du bist ein " + teamName);

        p.setDisplayName(ChatColor.GREEN + "[NWS] " + p.getName());
        p.setPlayerListName(ChatColor.GREEN + "[NWS] " + p.getName());
        p.setCustomName(ChatColor.GREEN + "[NWS] " + p.getName());


        if (!SchoolWars.gameStarted) {
            p.getInventory().addItem(new ItemStack(Items.nws_ranzen));
            ranzenAmount.put(teamName, ranzenAmount.get(teamName) + 1);
            p.getInventory().addItem(Items.schulbuch1);
        }

        p.teleport(new Location(p.getWorld(), 5, 81, 192, 90, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1, false, false, false));
    }

}
