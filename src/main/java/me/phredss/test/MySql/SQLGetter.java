package me.phredss.test.MySql;

import me.phredss.test.Test;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private Test plugin;
    public SQLGetter(Test plugin) {
        this.plugin = plugin;
    }
        //Creazione Table
        public void createTable() {
            System.out.println("1");
            PreparedStatement ps;
            try {
                System.out.println("2 1");
                ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS mobpoints "
                        + "(NAME VARCHAR (100),UUID VARCHAR (100),POINTS INT (100),PRIMARY KEY (NAME))");
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("2 2");
                e.printStackTrace();
            }
        }
        public void createPlayer(Player player) {
            try {
                UUID uuid = player.getUniqueId();
                if (!exists(uuid)) {
                    PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INFO mobpoints"
                            + " (NAME,UUID) VALUES (?,?)");
                    ps2.setString(1, player.getName());
                    ps2.setString(2, uuid.toString());
                    ps2.executeUpdate();

                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public boolean exists(UUID uuid) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM mobpoints WHERE UUID=?");
                ps.setString(1, uuid.toString());

                ResultSet results = ps.executeQuery();
                if (results.next()) {
                    //Player trovato(già dentro la tabella)
                    return true;
                }
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public void addPoints(UUID uuid, int points) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE mobpoints SET POINTS=? WHERE UUID=?");
                ps.setInt(1, (getPoints(uuid) + points));
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public int getPoints(UUID uuid) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT POINTS FROM mobpoints WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                int points = 0;
                if (rs.next()) {
                    points = rs.getInt("POINTS");
                    return points;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

}
