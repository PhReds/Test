package me.phredss.test;

import me.phredss.test.MySql.MySQL;
import me.phredss.test.MySql.SQLGetter;
import me.phredss.test.handler.JoinEvent;
import me.phredss.test.handler.MobKill;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public final class Test extends JavaPlugin {

    public MySQL SQL;
    public SQLGetter data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        //MySql
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);

        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("Database non è connesso");
        }

        if (SQL.isConnected()) {
            Bukkit.getLogger().info("Database connesso");
            data.createTable();
            getServer().getPluginManager().registerEvents(new JoinEvent(), this);
            getServer().getPluginManager().registerEvents(new MobKill(), this);
        }

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //MySql
        SQL.disconnect();
    }
}
