package edu.adlabs.flights;

import edu.adlabs.database.MyDB;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "FlightsWS")
@Stateless()
public class FlightsWS {

    /**
     * Dados un identificador de vuelo y una fecha, retorna el número de plazas
     * que están libres.
     *
     * Retorna -1 si no hay información para el avión y/o la fecha en cuestión
     *
     * @param id_vuelo representa el identificador de vuelo
     * @param fecha representa la fecha en la que se realiza el vuelo en formato
     * aaaammdd
     * @return el número de plazas libres en el vuelo
     */
    @WebMethod(operationName = "consulta_libres")
    public int consulta_libres(@WebParam(name = "id_vuelo") final int id_vuelo, @WebParam(name = "fecha") final int fecha) {
        Statement statement = MyDB.getStatement();
        String sql = "SELECT num_plazas_max - num_plazas_ocupadas as plazas_libres FROM vuelo_fecha WHERE id_vuelo = " + id_vuelo + " AND fecha = " + fecha;
        int plazasLibres = -1;
        try {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            plazasLibres = rs.getInt("plazas_libres");
        } catch (SQLException ex) {
            Logger.getLogger(FlightsWS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MyDB.disconnect();
        }
        return plazasLibres;
    }

    /**
     * Dados un identificador de vuelo y una fecha, reserva una plaza si quedan
     * plazas libres (incrementa el número de plazas ocupadas en un vuelo en una
     * fecha.
     *
     * Si es posible realizar la reserva, esta operación retorna el número de
     * plazas ocupadas que hay en el vuelo.
     *
     * Si no es posible realizar la reserva, esta operación retorna -1.
     *
     * @param id_vuelo representa el identificador de vuelo
     * @param fecha representa la fecha en la que se realiza el vuelo en formato
     * aaaammdd
     * @return el número de plazas ocupadas en el vuelo
     */
    @WebMethod(operationName = "reserva_plaza")
    public int reserva_plaza(@WebParam(name = "id_vuelo") final int id_vuelo, @WebParam(name = "fecha") final int fecha) {
        int plazasLibres = this.consulta_libres(id_vuelo, fecha);
        int plazasOcupadas = -1;
        if (plazasLibres > 0) {
            try {
                Statement statement = MyDB.getStatement();
                String sql = "UPDATE vuelo_fecha SET num_plazas_ocupadas = num_plazas_ocupadas + 1 WHERE id_vuelo = " + id_vuelo + " AND fecha = " + fecha;
                statement.execute(sql);
                sql = "SELECT num_plazas_ocupadas FROM vuelo_fecha WHERE id_vuelo = " + id_vuelo + " AND fecha = " + fecha;
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                plazasOcupadas = rs.getInt("num_plazas_ocupadas");
            } catch (SQLException ex) {
                Logger.getLogger(FlightsWS.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                MyDB.disconnect();
            }
        }
        return plazasOcupadas;
    }
}
