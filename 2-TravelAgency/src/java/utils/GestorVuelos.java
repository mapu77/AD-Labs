package utils;

import database.MyDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorVuelos {

    public List<String> getCompanyias() {
        Statement statment = MyDB.getStatment();
        try {
            ResultSet rs = statment.executeQuery("SELECT DISTINCT companyia FROM vuelos;");
            List<String> companyias = new ArrayList<>();
            while (rs.next()) {
                companyias.add(rs.getString("companyia"));
            }
            return companyias;
        } catch (SQLException ex) {
            Logger.getLogger(GestorVuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
