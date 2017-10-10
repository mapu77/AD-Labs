
package database;

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
            // create a database connection
            db.connection = DriverManager.getConnection("jdbc:sqlite:./example.db");
            db.statement = db.connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec. 
            
            statement.executeUpdate("drop table if exists usuarios");
            statement.executeUpdate("drop table if exists vuelos");
            statement.executeUpdate("drop table if exists hoteles");

            statement.executeUpdate("create table usuarios (id_usuario string primary key, password string)");
            statement.executeUpdate("insert into usuarios values('Edu','1234')");
            statement.executeUpdate("insert into usuarios values('Ian','1234')");
            statement.executeUpdate("create table vuelos (id_vuelo integer primary key, num_vuelo string,companyia string, origen string, hora_salida string, destino string, hora_llegada string)");
            statement.executeUpdate("create table hoteles (id_hotel integer primary key, nom_hotel string, cadena string,numb_hab integer,calle string, numero integer,codigo_postal string,ciudad string,provincia string,pais string)");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
