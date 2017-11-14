package edu.adlabs.hotels;

import edu.adlabs.database.MyDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "HotelsWS")
@Stateless()
public class HotelsWS {

    /**
     * Dados un identificador de hotel y una fecha, retorna el número de
     * habitaciones que están libres.
     *
     * Retorna -1 si no hay información disponible para el hotel y/o fecha en
     * cuestión
     *
     * @param id_hotel representa el identificador del hotel
     * @param fecha representa la fecha de la reserva en formato aaaammdd
     * @return el número de plazas libres en el hotel
     */
    @WebMethod(operationName = "consulta_libres")
    public int consulta_libres(@WebParam(name = "id_hotel") final int id_hotel, @WebParam(name = "fecha") final int fecha) {
        Statement statement = MyDB.getStatement();
        String sql = "SELECT num_hab_libres FROM hotel_fecha WHERE id_hotel = " + id_hotel + " AND fecha = " + fecha;
        int habLibres = -1;
        try {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            habLibres = rs.getInt("num_hab_libres");
        } catch (SQLException ex) {
            Logger.getLogger(HotelsWS.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MyDB.disconnect();
        }
        return habLibres;
    }

    /**
     * Dados un identificador de hotel y una fecha, reserva una habitación si
     * quedan habitaciones libres (incrementa el número de habitaciones ocupadas
     * en esa fecha en el hotel).
     *
     * Si es posible realizar la reserva, esta operación retorna el número de
     * habitaciones ocupadas que hay en el hotel.
     *
     * Si no es posible realizar la reserva, esta operación retorna -1.
     *
     * @param id_hotel representa el identificador del hotel
     * @param fecha representa la fecha de la reserva en formato aaaammdd
     * @return el número de plazas ocupadas en el hotel
     */
    @WebMethod(operationName = "reserva_habitacion")
    public int reserva_habitacion(@WebParam(name = "id_hotel") final int id_hotel, @WebParam(name = "fecha") final int fecha) {
        int habLibres = this.consulta_libres(id_hotel, fecha);
        int habOcupadas = -1;
        if (habLibres > 0) {
            try {
                Statement statement = MyDB.getStatement();
                String sql = "UPDATE hotel_fecha SET num_hab_ocupadas = num_hab_ocupadas + 1 WHERE id_hotel = " + id_hotel + " AND fecha = " + fecha;
                statement.execute(sql);
                sql = "UPDATE hotel_fecha SET num_hab_libres = num_hab_libres - 1 WHERE id_hotel = " + id_hotel + " AND fecha = " + fecha;
                statement.execute(sql);
                sql = "SELECT num_hab_ocupadas FROM hotel_fecha WHERE id_hotel = " + id_hotel + " AND fecha = " + fecha;
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                habOcupadas = rs.getInt("num_hab_ocupadas");
            } catch (SQLException ex) {
                Logger.getLogger(HotelsWS.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                MyDB.disconnect();
            }
        }
        return habOcupadas;
    }

}
