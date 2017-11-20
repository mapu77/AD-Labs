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
        List<String> cadenas = new ArrayList<>();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT cadena FROM hoteles where cadena is not null ORDER BY cadena asc;");
            while (rs.next()) {
                cadenas.add(rs.getString("cadena"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadenas;
    }

    public List<String> getCiudades() {
        Statement statment = MyDB.getStatement();
        List<String> ciudades = new ArrayList<>();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT ciudad FROM hoteles where ciudad is not null ORDER BY ciudad asc");
            while (rs.next()) {
                ciudades.add(rs.getString("ciudad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ciudades;
    }
}
