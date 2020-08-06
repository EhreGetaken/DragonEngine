package net.dragon.engine.driver;

import net.dragon.engine.DragonEngine;
import net.dragon.engine.logger.LogType;
import net.dragon.engine.logger.LoggerAPI;

import java.sql.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MySQL {

    private final String host;
    private final String user;
    private final String password;
    private final String database;
    private final String port;
    private Connection con;

    public MySQL(String host, String user, String password, String database, String port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    public void connect() {
        if (con == null)
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&autoReconnect=true");
                if (DragonEngine.getDragonEngine().getDebugMode()) {
                    LoggerAPI.log(LogType.INFO, "Conntecting to " + "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&autoReconnect=true");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public Connection getConnection() {
        return con;
    }

    public void close()
    {
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public void update(String sql) {

        if(isConnected()) {
            new FutureTask<>(new Runnable() {

                @Override
                public void run() {
                    try {
                        Statement s = con.createStatement();
                        s.executeUpdate(sql);
                        s.close();
                    } catch (SQLException e) {
                        System.out.println("An error occured while executing mysql update (" + e.getMessage() + ")!");
                    }
                }
            }, 1).run();
        }
    }

    public void createTable(String create) {
        update(create);
    }

    public boolean isConnected() {
        if (con != null) {
            return true;
        } else {
            return false;
        }
    }

    public ResultSet getResult(String qry) {
        if(isConnected()) {
            try {
                final FutureTask<ResultSet> task = new FutureTask<ResultSet>(new Callable<ResultSet>() {

                    PreparedStatement ps;

                    @Override
                    public ResultSet call() throws Exception {
                        ps = con.prepareStatement(qry);

                        return ps.executeQuery();
                    }
                });

                task.run();

                return task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            connect();
        }

        return null;
    }

}
