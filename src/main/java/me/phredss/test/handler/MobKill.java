package me.phredss.test.handler;

import me.phredss.test.MySql.MySQL;
import me.phredss.test.MySql.SQLGetter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKill implements Listener {

    public MySQL SQL;
    public SQLGetter data;

    @EventHandler
    public void onMobKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            Player player = (Player) e.getEntity().getKiller();
            data.addPoints(player.getUniqueId(), 1);
            player.sendMessage(ChatColor.GOLD + "Punti aggiunti: 1");
        }
    }

}
