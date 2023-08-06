package me.phredss.test.handler;

import me.phredss.test.MySql.MySQL;
import me.phredss.test.MySql.SQLGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.phredss.test.MySql.SQLGetter.*;
import me.phredss.test.Test.*;

public class JoinEvent implements Listener {

    public MySQL SQL;
    public SQLGetter data;



    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        this.data.createPlayer(player);
    }

}
