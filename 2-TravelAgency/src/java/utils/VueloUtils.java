package utils;

import database.MyDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VueloUtils {

    public List<String> getCompanyias() {
        Statement statment = MyDB.getStatement();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT companyia FROM vuelos;");
            List<String> companyias = new ArrayList<>();
            while (rs.next()) {
                companyias.add(rs.getString("companyia"));
            }
            return companyias;
        } catch (SQLException ex) {
            Logger.getLogger(VueloUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<String> getCiudades() {
        Statement statment = MyDB.getStatement();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT ciudad FROM (SELECT origen AS ciudad FROM vuelos UNION SELECT destino AS ciudad FROM vuelos) as mytable");
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
