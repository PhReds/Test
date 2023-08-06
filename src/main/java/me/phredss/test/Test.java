package me.phredss.test;

import me.phredss.test.db.Database;
import me.phredss.test.handler.Listeners;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;


public final class Test extends JavaPlugin {

    private Database database;

    @Override
    public void onEnable() {
        // Plugin startup logic

        try{
            this.database = new Database();
            database.initializeDatabase();
        }catch (SQLException e){

            System.out.println("Impossibile connettersi al database e creare la tabella");

            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new Listeners(this), this);

    }

    public Database getDatabase() {
        return database;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
