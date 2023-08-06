package me.phredss.test.handler;

import me.phredss.test.Test;
import me.phredss.test.models.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;
import java.util.Date;

public class Listeners implements Listener {


    private final Test plugin;

    public Listeners(Test plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreack(BlockBreakEvent e) {


        Player p = e.getPlayer();

        try{
            PlayerStats stats = this.plugin.getDatabase().findPlayerStatsByUUID(p.getUniqueId().toString());

            if (stats == null) {

                stats = new PlayerStats(p.getUniqueId().toString(), 0, 0, 1, 0, new Date(), new Date());

                this.plugin.getDatabase().createPlayerStats(stats);


            }else{

                stats.setBlocksBroken(stats.getBlocksBroken() + 1);
                this.plugin.getDatabase().updatePlayerStats(stats);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }

    }

}
