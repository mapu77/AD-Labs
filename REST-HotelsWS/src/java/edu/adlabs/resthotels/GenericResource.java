/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adlabs.resthotels;

import edu.adlabs.database.MyDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

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
    @Path("consulta")
    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String consulta_libres(@QueryParam("id_hotel") final int id_hotel, @QueryParam("fecha") final int fecha) {
        Statement statement = MyDB.getStatement();
        String sql = "SELECT num_hab_libres FROM hotel_fecha WHERE id_hotel = " + id_hotel + " AND fecha = " + fecha;
        int habLibres = -1;
        try {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            habLibres = rs.getInt("num_hab_libres");
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MyDB.disconnect();
        }
        return String.valueOf(habLibres);
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
    @Path("reserva")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public String reserva_habitacion(@FormParam("id_hotel") final int id_hotel, @FormParam("fecha") final int fecha) {
        String habLibres = this.consulta_libres(id_hotel, fecha);
        int habOcupadas = -1;
        if (Integer.valueOf(habLibres) > 0) {
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
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                MyDB.disconnect();
            }
        }
        return String.valueOf(habOcupadas);
    }

    /**
     * Retrieves representation of an instance of
     * edu.adlabs.resthotels.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<html><head/><body><h1>Hello World!</h1></body></html>";
    }
}
