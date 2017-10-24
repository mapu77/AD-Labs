package edu.adlabs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDB {

    private static Connection connection;
    private static Statement statement;
    private static MyDB db;

    public static Statement getStatement() {
        if (db.connection != null) {
            return db.statement;
        } else {
            db = new MyDB();
            return db.statement;
        }
    }

    private MyDB() {
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");
            // create a edu.adlabs.database connection
            db.connection = DriverManager.getConnection("jdbc:sqlite:./example_hotel.db");
            db.statement = db.connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec. 
            statement.executeUpdate("drop table if exists hotel_fecha");
            statement.executeUpdate("create table hotel_fecha (id_hotel INTEGER PRIMARY KEY, fecha INTEGER, num_hab_ocupadas INTEGER, num_hab_libres INTEGER)");
            statement.executeUpdate("insert into hotel_fecha VALUES (1, 20170101, 15, 15 )");
            statement.executeUpdate("insert into hotel_fecha VALUES (2, 20170231, 2, 35 )");
            statement.executeUpdate("insert into hotel_fecha VALUES (3, 20170524, 0, 6 )");
            statement.executeUpdate("insert into hotel_fecha VALUES (4, 20170911, 0, 1714 )");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
