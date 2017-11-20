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
        List<String> companyias = new ArrayList<>();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT companyia FROM vuelos where companyia is not null order by companyia asc;");
            while (rs.next()) {
                companyias.add(rs.getString("companyia"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MyDB.disconnect();
        }
        return companyias;
    }

    public List<String> getCiudades() {
        Statement statment = MyDB.getStatement();
        List<String> ciudades = new ArrayList<>();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT ciudad FROM (SELECT origen AS ciudad FROM vuelos where ciudad is not null UNION SELECT destino AS ciudad FROM vuelos where ciudad is not null) as mytable");
            while (rs.next()) {
                ciudades.add(rs.getString("ciudad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VueloUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MyDB.disconnect();
        }
        return ciudades;
    }
}
