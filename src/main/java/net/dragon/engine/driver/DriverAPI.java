package net.dragon.engine.driver;

import net.dragon.engine.DragonEngine;
import net.dragon.engine.logger.LogType;
import net.dragon.engine.logger.LoggerAPI;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverAPI {

    public DriverAPI() {
        if (DragonEngine.getDragonEngine().getDebugMode()) {
            LoggerAPI.log(LogType.INFO, "Initialising " + DriverAPI.class);
        }
    }

    public boolean dataInRowExists(String row, String table, String input, MySQL mySQL) {
        ResultSet resultSet = mySQL.getResult("SELECT " + row + " FROM " + table + " WHERE " + row + "='" + input + "'");

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    return true;
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
        return false;
    }

    public void createData(String insert, MySQL mySQL) {
        mySQL.update(insert);
    }

    public String getString(String row, String table, String get, String input, MySQL mySQL) {
        ResultSet rs = mySQL.getResult("SELECT " + row + " FROM " + table + " WHERE " + get + "='" + input + "'");
        String result = "null";

        try {
            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString(row);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    public void setString(String row, String table, String get, String set, String input, MySQL mySQL) {
        if (dataInRowExists(row, table, input, mySQL)) {
            mySQL.update("UPDATE " + table + " SET " + row + "='" + set + "' WHERE " + get + "='" + input + "'");
        }
    }

    public int getInteger(String row, String table, String get, int input, MySQL mySQL) {
        ResultSet rs = mySQL.getResult("SELECT " + row + " FROM " + table + " WHERE " + get + "='" + input + "'");
        int i = 0;

        try {
            if (rs != null) {
                while (rs.next()) {
                    i = rs.getInt(row);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return i;
    }

    public void setInteger(String row, String table, String get, int set, String input, MySQL mySQL) {
        if (dataInRowExists(row, table, input, mySQL)) {
            mySQL.update("UPDATE " + table + " SET " + row + "='" + set + "' WHERE " + get + "='" + input + "'");
        }
    }

}
