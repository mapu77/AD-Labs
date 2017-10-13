package utils;

import database.MyDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelUtils {
 
    public List<String> getCadenas() {
        Statement statment = MyDB.getStatement();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT cadena FROM hoteles;");
            List<String> cadenas = new ArrayList<>();
            while (rs.next()) {
                cadenas.add(rs.getString("cadena"));
            }
            return cadenas;
        } catch (SQLException ex) {
            Logger.getLogger(VueloUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<String> getCiudades() {
        Statement statment = MyDB.getStatement();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT ciudad FROM hoteles");
            List<String> ciudades = new ArrayList<>();
            while (rs.next()) {
                ciudades.add(rs.getString("ciudad"));
            }
            return ciudades;
        } catch (SQLException ex) {
            Logger.getLogger(VueloUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
